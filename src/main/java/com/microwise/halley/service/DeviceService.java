package com.microwise.halley.service;

import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.RealtimeDataVO;

import java.util.List;

/**
 * 设备 Service
 */
public interface DeviceService {

    /**
     * 查询某次外展中指定类型的设备(按监测指标过滤设备)
     *
     * @param exhibitionId     外展ID
     * @param sensorPhysicalId 监测指标ID
     * @return 设备集合
     */
    public List<NodeInfoPO> findNodeListBySensorId(int exhibitionId, int sensorPhysicalId);

    /**
     * 通过设备id编号获得设备实时数据
     *
     * @param deviceId 设备id 编号
     * @return RealtimeDataVO 设备的实时数据vo对象
     * @author 许保吉
     * @date 2013-1-18
     */
    public RealtimeDataVO findRealtimeData(String deviceId);
}
