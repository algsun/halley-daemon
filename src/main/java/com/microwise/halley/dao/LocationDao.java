package com.microwise.halley.dao;

import com.microwise.halley.bean.po.LocationPO;
import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.*;

import java.util.Date;
import java.util.List;

/**
 * 设备 Dao
 *
 * @author xuyuexi
 * @date 2014.08.14
 */
public interface LocationDao {

    /**
     * 查询车辆位置点
     *
     * @param carId            车辆位置点
     * @param sensorPhysicalId 监测指标ID
     * @return
     */
    public List<LocationVO> findLocationsByCarId(int carId, int sensorPhysicalId);

    /**
     * 查询外展位置点
     *
     * @param exhibitionId 外展ID
     * @return 位置点集合
     */
    public List<LocationVO> findLocationsByExhibitionId(int exhibitionId);

    /**
     * 查询某次外展中指定类型的设备(按监测指标过滤设备)
     *
     * @param exhibitionId     外展ID
     * @param sensorPhysicalId 监测指标ID
     * @return 设备集合
     */
    public List<LocationVO> findLocationsBySensorId(int exhibitionId, int sensorPhysicalId);

    /**
     * 查询设备实时数据信息
     *
     * @param locationId 设备编号
     * @return RealTimeDataVO 实时数据VO 对象 此处未封装设备监测指标数据
     */
    public RealtimeDataVO findRealtimeData(String locationId);

    /**
     * 获得设备监测指标实时数据
     *
     * @param locationId           设备编号
     * @param sensorPhysicalIdList 筛选条件监测指标集合
     * @return List<SensorInfoVO> 监测指标数据信息列表
     */
    public List<LocationDataVO> findLocationDataVO(String locationId,
                                                   List<Integer> sensorPhysicalIdList);

    /**
     * 根据位置点ID查询位置点信息
     *
     * @param locationId 位置点ID
     * @return 位置点
     */
    public LocationVO findLocationById(String locationId);

    /**
     * 查询设备历史表中指定ID后的历史数据
     *
     * @param locationId 位置点编号
     * @param id         历史数据ID
     * @return List<LocationDataVO> 实时数据VO 对象 此处未封装设备监测指标数据
     * @author xuyuexi
     * @date 2014-8-19
     */
    public List<LocationDataVO> findHistoryDataList(String locationId, int id);


}
