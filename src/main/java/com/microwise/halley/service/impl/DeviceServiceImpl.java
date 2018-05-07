package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.DeviceDataVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;
import com.microwise.halley.dao.DeviceDao;
import com.microwise.halley.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-11
 */
@Service
@Scope("prototype")
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public List<NodeInfoPO> findNodeListBySensorId(int exhibitionId, int sensorPhysicalId) {
        return deviceDao.findNodeListBySensorId(exhibitionId, sensorPhysicalId);
    }

    @Override
    public RealtimeDataVO findRealtimeData(String deviceId) {
        RealtimeDataVO realtimeDataVO = deviceDao.findNodeInfo(deviceId);
        List<DeviceDataVO> sensorInfoList = deviceDao.findNodeSensor(
                realtimeDataVO.getNodeId(), null);
        Map<Integer, DeviceDataVO> sensorInfoMap = new HashMap<Integer, DeviceDataVO>();
        for (DeviceDataVO deviceData : sensorInfoList) {
            sensorInfoMap.put(deviceData.getSensorPhysicalid(), deviceData);
        }
        realtimeDataVO.setSensorInfoMap(sensorInfoMap);
        return realtimeDataVO;
    }
}
