package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.*;
import com.microwise.halley.dao.DeviceDao;
import com.microwise.halley.dao.LocationDao;
import com.microwise.halley.service.DeviceService;
import com.microwise.halley.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 位置点 Service 实现
 *
 * @author xuyuexi
 * @date 2014-08-14
 */
@Service
@Scope("prototype")
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;


    /**
     * 设备检测指标ID:经纬度
     */
    public static final int LONGITUDE_ID = 12287;
    public static final int LATITUDE_ID = 12286;

    @Override
    public List<LocationVO> findLocationsBySensorId(int exhibitionId, int sensorPhysicalId) {
        return locationDao.findLocationsBySensorId(exhibitionId, sensorPhysicalId);
    }

    @Override
    public RealtimeDataVO findRealtimeData(String locationId) {
        RealtimeDataVO realtimeDataVO = locationDao.findRealtimeData(locationId);
        LocationVO locationVO = locationDao.findLocationById(locationId);
        if (realtimeDataVO != null) {
            Map<Integer, LocationDataVO> map = new HashMap<Integer, LocationDataVO>();
            List<LocationDataVO> realtimeDataList = locationDao.findLocationDataVO(locationVO.getNodeId(), locationVO.getSensorIdList());
            for (LocationDataVO locationDataVO : realtimeDataList) {
                map.put(locationDataVO.getSensorPhysicalid(), locationDataVO);
            }
            realtimeDataVO.setLocationSensorInfoMap(map);
        }
        return realtimeDataVO;
    }


    @Override
    public List<LocationDataVO> findHistoryDataList(String locationId, int id) {
        return locationDao.findHistoryDataList(locationId, id);
    }

    @Override
    public List<LocationVO> findLocationsByCarId(int carId, int sensorPhysicalId) {
        return locationDao.findLocationsByCarId(carId, sensorPhysicalId);
    }
}
