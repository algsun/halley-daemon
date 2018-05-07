package com.microwise.halley.service;

import com.microwise.halley.bean.vo.LocationDataVO;
import com.microwise.halley.bean.vo.LocationVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;
import com.microwise.halley.bean.vo.RecentDataVO;

import java.util.List;

/**
 * 位置点 Service
 */
public interface LocationService {

    /**
     * 查询外展车辆位置点
     *
     * @param carId            车辆id
     * @param sensorPhysicalId 监测指标ID
     * @return
     */
    public List<LocationVO> findLocationsByCarId(int carId, int sensorPhysicalId);

    /**
     * 查询某次外展中指定类型的位置点(按监测指标过滤位置点)
     *
     * @param exhibitionId     外展ID
     * @param sensorPhysicalId 监测指标ID
     * @return 位置点集合
     */
    public List<LocationVO> findLocationsBySensorId(int exhibitionId, int sensorPhysicalId);

    /**
     * 通过位置点id编号获得位置点实时数据
     *
     * @param locationId 位置点 编号
     * @return RealtimeDataVO 设备的实时数据vo对象
     * @author 许月希
     * @date 2014-8-14
     */
    public RealtimeDataVO findRealtimeData(String locationId);

    /**
     * 通过位置点id获得设备指定ID后的历史数据
     *
     * @param locationId 位置点id
     * @param id         历史数据ID
     * @return 设备的历史数据vo对象集合
     */
    public List<LocationDataVO> findHistoryDataList(String locationId, int id);
}
