package com.microwise.halley.quartz;

import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.microwise.halley.bean.po.AlarmPO;
import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.PathPO;
import com.microwise.halley.bean.po.RouteHistoryPO;
import com.microwise.halley.bean.vo.*;
import com.microwise.halley.common.SysConfig;
import com.microwise.halley.service.*;
import com.microwise.halley.util.SmsUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报警任务
 *
 * @author li.jianfei, xu.yuexi
 * @date 2013-11-14
 * @check 2014-5-22 li.jianfei #8578
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AlarmQuartzJob implements Job {

    public static final Logger log = LoggerFactory.getLogger(AlarmQuartzJob.class);

    /**
     * 地球半径,单位千米
     */
    private static final double EARTH_RADIUS = 6378.137;


    private ExhibitionService exhibitionService;

    private ExhibitionStateService exhibitionStateService;

    private CarService carService;

    // private DeviceService deviceService;

    private PathService pathService;

    private UserService userService;

    private AlarmService alarmService;

    private LocationService locationService;

    private RouteHistoryService routeHistoryService;

    /**
     * 设备检测指标ID:经度
     */
    public static final int SENSORPHYSICALID_LONGITUDE = 12287;
    public static final int SENSORPHYSICALID_LATITUDE = 12286;
    /**
     * 开关量监测指标ID
     */
    public static final int SWH_ID = 89;
    /**
     * 红外开关ID
     */
    public static final int IR = 115;
    /**
     * 微动开关
     */
    public static final int SM = 116;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        exhibitionService = (ExhibitionService) jobExecutionContext.getTrigger().getJobDataMap().get("exhibitionService");
        exhibitionStateService = (ExhibitionStateService) jobExecutionContext.getTrigger().getJobDataMap().get("exhibitionStateService");
        carService = (CarService) jobExecutionContext.getTrigger().getJobDataMap().get("carService");
        //  deviceService = (DeviceService) jobExecutionContext.getTrigger().getJobDataMap().get("deviceService");
        pathService = (PathService) jobExecutionContext.getTrigger().getJobDataMap().get("pathService");
        userService = (UserService) jobExecutionContext.getTrigger().getJobDataMap().get("userService");
        alarmService = (AlarmService) jobExecutionContext.getTrigger().getJobDataMap().get("alarmService");
        locationService = (LocationService) jobExecutionContext.getTrigger().getJobDataMap().get("locationService");
        routeHistoryService = (RouteHistoryService) jobExecutionContext.getTrigger().getJobDataMap().get("routeHistoryService");
        log.info("报警定时任务");
        doorAlarm();
        pathAlarm();
    }

    /**
     * 开关门报警业务
     */
    private void doorAlarm() {
        try {
            // 查询所有未结束的外展记录
            List<ExhibitionVO> exhibitionList = exhibitionService.findExhibitionListNotEnd();

            for (ExhibitionVO exhibition : exhibitionList) {
                int exhibitionId = exhibition.getExhibitionId();
                List<CarPO> cars = carService.findCarsByExhibitionId(exhibitionId);
                for (CarPO car : cars) {
                    List<LocationVO> locations = locationService.findLocationsByCarId(car.getId(), IR);
                    List<LocationVO> smLocations = locationService.findLocationsByCarId(car.getId(), SM);
                    List<LocationVO> swLocations = locationService.findLocationsByCarId(car.getId(), SWH_ID);
                    locations.addAll(smLocations);
                    locations.addAll(swLocations);
                    for (LocationVO location : locations) {
                        RealtimeDataVO realtimeData = locationService.findRealtimeData(location.getId());
                        Date lastStamp = SysConfig.swhLastDate.get("" + car.getId());
                        if (lastStamp == null || lastStamp.before(realtimeData.getStamp())) {
                            LocationDataVO locationDataVO;
                            LocationDataVO irLocationDataVO = realtimeData.getLocationSensorInfoMap().get(IR);
                            LocationDataVO smLocationDataVO = realtimeData.getLocationSensorInfoMap().get(SM);
                            LocationDataVO swLocationDataVO = realtimeData.getLocationSensorInfoMap().get(SWH_ID);
                            if (irLocationDataVO != null) {
                                locationDataVO = irLocationDataVO;
                            } else if (smLocationDataVO != null) {
                                locationDataVO = smLocationDataVO;
                            } else {
                                locationDataVO = swLocationDataVO;
                            }
                            List<UserVO> userList = userService.findUsersByExhibitionId(exhibition.getExhibitionId());//报警通知人
                            int doorState = Integer.parseInt(locationDataVO.getSensorPhysicalValue());//车门状态
                            if (SysConfig.swhMsgState.get("" + car.getId()) == null || doorState != SysConfig.swhMsgState.get("" + car.getId())) {
                                String address = "";
                                List<LocationVO> seLocations = locationService.findLocationsByCarId(car.getId(), SENSORPHYSICALID_LONGITUDE);
                                for (LocationVO locationVO : seLocations) {
                                    Map map = getCurrentLocation(locationVO.getId());
                                    Map<String, String> lngLat = gpsConvertToGCJ02(map.get("longitude").toString(), map.get("latitude").toString());
                                    address = getAddr(lngLat.get("latitude"), lngLat.get("longitude"));
                                }
                                for (UserVO user : userList) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String content = "【元智系统】" + user.getUserName() + (Strings.isNullOrEmpty(user.getMobile()) ? "" : "(" + (user.getMobile()) + ")") + "你好，"
                                            + "外展车辆" + car.getPlateNumber() + "于" + sdf.format(realtimeData.getStamp()) + "在" + address + (doorState == 1 ? "打开" : "关闭") + "车门！";
                                    SmsUtil.sendSms(content, user.getMobile());
                                    log.info("开关门报警：" + content);
                                    alarmService.save(new AlarmPO(exhibition.getExhibitionId(), content, user.getId(), realtimeData.getStamp()));
                                }
                                SysConfig.swhMsgState.put("" + car.getId(), doorState);
                            }
                            SysConfig.swhLastDate.put("" + car.getId(), realtimeData.getStamp());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 路线偏离报警业务
     */
    private void pathAlarm() {
        try {
            // 查询所有未结束的外展记录
            List<ExhibitionVO> exhibitionList = exhibitionService.findExhibitionListNotEnd();

            for (ExhibitionVO exhibition : exhibitionList) {
                int exhibitionId = exhibition.getExhibitionId();

                //根据外展ID查询预设路线的拐点集合
                List<PathPointsVO> points = pathService.findExhibitionPathPoints(exhibitionId);
                if (points.size() == 0) continue;

                //按监测指标过滤位置点
                List<LocationVO> locationsBySensorId = locationService.findLocationsBySensorId(exhibitionId, SENSORPHYSICALID_LONGITUDE);
                //获取某次外展的历史操作
                List<ExhibitionStateVO> exhibitionStateList = exhibitionStateService.getHistoryState(exhibitionId);
                //过滤预设路线拐点
                List<PathPointsVO> filteredPoints = pointsFilter(points, exhibitionStateList, exhibitionId);
                for (LocationVO locationVO : locationsBySensorId) {

                    AlarmConfigsVO config = pathService.findAlarmConfigs(exhibitionId);
                    if (config == null) continue;

                    CarPO car = carService.findCarByLocationId(exhibitionId, locationVO.getId());
                    if (car == null) continue;

                    List<UserVO> users = userService.findUsersByExhibitionId(exhibitionId);
                    if (users.isEmpty()) continue;

                    List<RouteHistoryPO> routeHistorys = routeHistoryService.findRouteHistory(car.getId());
                    if (routeHistorys.size() == 0) continue;
                    RouteHistoryPO routeHistory = routeHistorys.get(0);

                    if (routeHistory == null) continue;
                    Double longitude = routeHistory.getLongitude();
                    Double latitude = routeHistory.getLatitude();

                    //计算实时点到预设路线的最短距离，单位:米
                    Double range = calculateShortestRange(filteredPoints, latitude, longitude);


                    //报警
                    int alarmRange = config.getAlarmRange() * 1000; // 公里转为米
                    Date lastStamp = SysConfig.pathLastDate.get("" + car.getId());
                    if (lastStamp == null
                            || lastStamp.before(routeHistory.getTime())) {
                        int pathState = range > alarmRange ? 0 : 1;
                        if (SysConfig.pathMsgState.get("" + car.getId()) == null
                                || pathState != SysConfig.pathMsgState.get("" + car.getId())) {
                            String address = getAddr(latitude.toString(), longitude.toString());
                            for (UserVO user : users) {
                                String content = "【元智系统】" + user.getUserName() + (Strings.isNullOrEmpty(user.getMobile()) ? "" : "(" + (user.getMobile()) + ")") + "你好，"
                                        + "外展车辆(" + car.getPlateNumber() + ")" + (pathState == 1 ? "重回" : "偏离") + "预设路线范围，目前行驶至" + address + "。";
                                SmsUtil.sendSms(content, user.getMobile());
                                alarmService.save(new AlarmPO(exhibitionId, content, user.getId(), routeHistory.getTime()));
                                log.info("车辆超范围报警" + content);
                            }

                            SysConfig.pathMsgState.put("" + car.getId(), (range > alarmRange ? 0 : 1));
                        }
                    }
                    SysConfig.pathLastDate.put("" + car.getId(), routeHistory.getTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤预设路线的所有拐点集合
     * 只返回当前的历史操作所经过的所有的点
     *
     * @param points              所有的拐点集合
     * @param exhibitionStateList 历史操作集合
     * @param exhibitionId        外展ID
     * @return 过滤后的拐点集合
     */
    private List<PathPointsVO> pointsFilter(List<PathPointsVO> points, List<ExhibitionStateVO> exhibitionStateList, int exhibitionId) {
        //出发地
        PathPO startPO = exhibitionStateService.getStartDestination(exhibitionId);
        List<PathPointsVO> pathPointList = new ArrayList<PathPointsVO>();
        for (ExhibitionStateVO exhibitionState : exhibitionStateList) {
            if (exhibitionState.getState() == 2) {//只需要"运输中"状体下的所有拐点
                PathPO startPathPoint = exhibitionState.getStartPathPO();
                PathPO nearByStart = new PathPO();
                //如果当前出发地是起点，则直接使用预设路线的第一个点作为出发地
                //否侧查询里出发地的该点最近的一个拐点
                if (startPO.getId() == startPathPoint.getId()) {
                    nearByStart.setLatitude(points.get(0).getLatitude());
                    nearByStart.setLongitude(points.get(0).getLongitude());
                } else {
                    nearByStart = exhibitionStateService.getNearBy(exhibitionId, startPathPoint.getLongitude(), startPathPoint.getLatitude());
                }
                PathPO endPathPoint = exhibitionState.getEndPathPO();
                PathPO nearByEnd = exhibitionStateService.getNearBy(exhibitionId, endPathPoint.getLongitude(), endPathPoint.getLatitude());
                for (int i = 0; i < points.size(); i++) {
                    PathPointsVO pathPoint = points.get(i);
                    if (pathPoint.getId() == nearByStart.getId()) {
                        for (int j = i; j < points.size(); j++) {
                            if (pathPoint.getId() != nearByEnd.getId()) {
                                pathPointList.add(points.get(j));
                            } else {
                                break;//break,跳出当前的循环
                            }
                        }
                    }
                }
            }
        }
        return pathPointList;
    }

    private Map<String, Object> getCurrentLocation(String locationId) {
        String node = "";
        Double longitude = null;
        Double latitude = null;
        RealtimeDataVO realtimeData = locationService.findRealtimeData(locationId);
        LocationDataVO locationDataLng = realtimeData.getLocationSensorInfoMap().get(SENSORPHYSICALID_LONGITUDE);
        LocationDataVO locationDataLat = realtimeData.getLocationSensorInfoMap().get(SENSORPHYSICALID_LATITUDE);
        if (locationDataLng != null && locationDataLat != null) {
            node = locationId;
            longitude = Double.parseDouble(locationDataLng.getSensorPhysicalValue());
            latitude = Double.parseDouble(locationDataLat.getSensorPhysicalValue());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("locationId", node);
        map.put("stamp", realtimeData.getStamp());
        return map;
    }

    /**
     * 获取某点到预设路线的最短距离，作为报警设置的判断
     *
     * @param points 预设路线的拐点集合
     * @param lat    某点的纬度
     * @param lng    某点的经度
     * @return 最短距离
     */
    private double calculateShortestRange(List<PathPointsVO> points, double lat, double lng) {
        Set<Double> list = new TreeSet<Double>();
        for (int i = 0; i < points.size() - 1; i++) {
            PathPointsVO point = points.get(i);
            Double dis = getDistance(point.getLatitude(), point.getLongitude(), lat, lng);
            BigDecimal bd = new BigDecimal(dis);
            double d = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            list.add(d);
        }
        Iterator it = list.iterator();
        return it.hasNext() ? (Double) it.next() : 0;
    }

    /**
     * 计算google地图两点间距离
     *
     * @param lat1 point1纬度
     * @param lng1 point1纬度
     * @param lat2 point2纬度
     * @param lng2 point2纬度
     * @return 距离，单位米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000.0;
        return s * 1000;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 根据经纬度反向解析地址，有时需要多尝试几次
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return
     */
    public String getAddr(String latitude, String longitude) {
        String addr = "";
        String url = String.format(
                "http://restapi.amap.com/v3/geocode/regeo?extensions=all&key=eaa6713cc90f6a8f556c3f6ff1ca0542&location=" + longitude + "," + latitude + "&radius=3000&length=600");
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                /*读返回数据*/
                InputStreamReader reader = new InputStreamReader(response
                        .getEntity().getContent(), "utf-8");
                String resultText = CharStreams.toString(reader);
                Gson gson = new Gson();
                Map<String, Object> map = gson.fromJson(resultText, Map.class);
                Map<String, String> regeocode = (Map) map.get("regeocode");
                addr = regeocode.get("formatted_address");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addr;
    }


    /**
     * 1.发送HTTP请求进行坐标转化，GPS坐标转GOOGLE坐标
     * 2.对结果进行base64解码
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return 结果集map
     */
    public Map<String, String> gpsConvertToGCJ02(String longitude, String latitude) {
        Map<String, String> convertedMap = new HashMap<String, String>();
        String httpUrl = "http://api.map.baidu.com/ag/coord/convert?x=" + longitude + "&y=" + latitude + "&from=0&to=2&mode=1";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(httpUrl);
            HttpResponse response = httpClient.execute(get);
            InputStreamReader reader = new InputStreamReader(response
                    .getEntity().getContent(), "utf-8");
            String resultText = CharStreams.toString(reader);
            JSONObject jsonObject = new JSONObject(resultText.substring(1, resultText.length() - 1));
            String base64Longitude = (String) jsonObject.get("x");
            String base64Latitude = (String) jsonObject.get("y");
            BASE64Decoder base64Decoder = new BASE64Decoder();


            convertedMap.put("longitude", new String(base64Decoder.decodeBuffer(base64Longitude)));
            convertedMap.put("latitude", new String(base64Decoder.decodeBuffer(base64Latitude)));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertedMap;
    }

}
