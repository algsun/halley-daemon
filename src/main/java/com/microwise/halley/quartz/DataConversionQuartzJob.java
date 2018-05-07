package com.microwise.halley.quartz;

import com.google.common.io.CharStreams;
import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.RouteHistoryPO;
import com.microwise.halley.bean.vo.CoordinatesVO;
import com.microwise.halley.bean.vo.ExhibitionVO;
import com.microwise.halley.bean.vo.LocationDataVO;
import com.microwise.halley.bean.vo.LocationVO;
import com.microwise.halley.common.SysConfig;
import com.microwise.halley.service.CarService;
import com.microwise.halley.service.ExhibitionService;
import com.microwise.halley.service.LocationService;
import com.microwise.halley.service.RouteHistoryService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.InputStreamReader;
import java.util.*;

/**
 * 数据
 */
@Deprecated
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DataConversionQuartzJob implements Job {

    public static final Logger log = LoggerFactory.getLogger(DataConversionQuartzJob.class);

    private ExhibitionService exhibitionService;
    private CarService carService;
    //  private DeviceService deviceService;
    private LocationService locationService;
    private RouteHistoryService routeHistoryService;


    /**
     * 设备检测指标ID:经纬度
     */
    public static final int LONGITUDE_ID = 12287;
    public static final int LATITUDE_ID = 12286;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行经纬度数据拷贝");
        exhibitionService = (ExhibitionService) jobExecutionContext.getTrigger().getJobDataMap().get("exhibitionService");
        carService = (CarService) jobExecutionContext.getTrigger().getJobDataMap().get("carService");
        //   deviceService = (DeviceService) jobExecutionContext.getTrigger().getJobDataMap().get("deviceService");
        locationService = (LocationService) jobExecutionContext.getTrigger().getJobDataMap().get("locationService");
        routeHistoryService = (RouteHistoryService) jobExecutionContext.getTrigger().getJobDataMap().get("routeHistoryService");
        locationService = (LocationService) jobExecutionContext.getTrigger().getJobDataMap().get("locationService");
        //dateConversion();
    }

    private void dateConversion() {
        CoordinatesVO coordinates;

        // 查询所有未结束的外展记录
        List<ExhibitionVO> exhibitionList = exhibitionService.findExhibitionListNotEnd();

        for (ExhibitionVO exhibition : exhibitionList) {
            log.info("经纬度数据 exhibitionId:　" + exhibition.getExhibitionId());
            List<LocationVO> locationVOs = locationService.findLocationsBySensorId(exhibition.getExhibitionId(), LATITUDE_ID);
            for (LocationVO location : locationVOs) {
                coordinates = new CoordinatesVO();

                CarPO car = carService.findCarByLocationId(exhibition.getExhibitionId(), location.getId());
                Integer lastId = SysConfig.routeHistoryLastId.get("" + car.getId());
                if (lastId == null) {
                    lastId = routeHistoryService.findLastConversionId(location.getId(), car.getId(), exhibition.getExhibitionId());
                    SysConfig.routeHistoryLastId.put("" + car.getId(), lastId);
                }
                log.info("经纬度数据 lastId:　" + lastId);

                if (lastId != 0) {
                    List<LocationDataVO> locationDataList = locationService.findHistoryDataList(location.getId(), lastId);

                    LocationDataVO lastLocationData = null;
                    List<Map<Integer, LocationDataVO>> dataList = new ArrayList<Map<Integer, LocationDataVO>>();
                    Map<Integer, LocationDataVO> data = null;
                    for (Iterator iterator = locationDataList.iterator(); iterator.hasNext(); ) {
                        LocationDataVO locationData = (LocationDataVO) iterator.next();
                        if (lastLocationData == null || lastLocationData.getStamp().getTime() != locationData.getStamp().getTime()) {
                            if (data != null && data.containsKey(LONGITUDE_ID) && data.containsKey(LATITUDE_ID)) {
                                dataList.add(data);
                            }
                            data = new HashMap<Integer, LocationDataVO>();
                        }
                        if (locationData.getSensorPhysicalid() == LONGITUDE_ID) {
                            data.put(LONGITUDE_ID, locationData);
                        } else if (locationData.getSensorPhysicalid() == LATITUDE_ID) {
                            data.put(LATITUDE_ID, locationData);
                        }
                        SysConfig.routeHistoryLastId.put("" + car.getId(), locationData.getId());
                        lastLocationData = locationData;
                        if (!iterator.hasNext()) {
                            dataList.add(data);
                        }
                    }

                    for (Map<Integer, LocationDataVO> dataMap : dataList) {
                        LocationDataVO lngData = dataMap.get(LONGITUDE_ID);
                        LocationDataVO latData = dataMap.get(LATITUDE_ID);

                        if (coordinates != null) {
                            coordinates.setLongitude(Double.parseDouble(lngData.getSensorPhysicalValue()));
                            coordinates.setLatitude(Double.parseDouble(latData.getSensorPhysicalValue()));
                            coordinates = gpsConvertToGoogleXY(coordinates);
                        }
                        if (coordinates == null) continue;

                        // 封装数据
                        RouteHistoryPO routeHistory = new RouteHistoryPO();
                        routeHistory.setCarId(car.getId());
                        routeHistory.setLongitude(coordinates.getLongitude());
                        routeHistory.setLatitude(coordinates.getLatitude());
                        routeHistory.setTime(lngData.getStamp());
                        if (!routeHistoryService.exists(routeHistory)) {
                            routeHistoryService.saveRouteHistory(routeHistory);
                        }
                    }
                }

            }
        }
    }


    /**
     * 1.发送HTTP请求进行坐标转化，GPS坐标转GOOGLE坐标
     * 2.对结果进行base64解码
     *
     * @param coordinates 经纬度对象
     * @return 转换后的经纬度对象
     */
    public CoordinatesVO gpsConvertToGoogleXY(CoordinatesVO coordinates) {
        String httpUrl = "http://api.map.baidu.com/ag/coord/convert?x=" + coordinates.getLongitude() + "&y="
                + coordinates.getLatitude() + "&from=0&to=2&mode=1";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(httpUrl);
            HttpResponse response = httpClient.execute(get);
            InputStreamReader reader = new InputStreamReader(response
                    .getEntity().getContent(), "utf-8");
            String resultText = CharStreams.toString(reader);

            log.info("经纬度转换" + resultText);

            JSONObject jsonObject = new JSONObject(resultText.substring(1, resultText.length() - 1));
            String base64Longitude = (String) jsonObject.get("x");
            String base64Latitude = (String) jsonObject.get("y");
            BASE64Decoder base64Decoder = new BASE64Decoder();

            coordinates.setLongitude(Double.parseDouble(new String(base64Decoder.decodeBuffer(base64Longitude))));
            coordinates.setLatitude(Double.parseDouble(new String(base64Decoder.decodeBuffer(base64Latitude))));
        } catch (Exception e) {
            coordinates = null;
            e.printStackTrace();
        }
        return coordinates;
    }
}
