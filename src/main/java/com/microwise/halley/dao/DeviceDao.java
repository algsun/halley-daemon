package com.microwise.halley.dao;

import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.DeviceDataVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;

import java.util.List;

/**
 * 设备 Dao
 *
 * @author li.jianfei
 * @date 2013.11.08
 */
public interface DeviceDao {

    /**
     * 查询外展节点设备
     *
     * @param exhibitionId 外展ID
     * @return 设备集合
     */
    public List<NodeInfoPO> findNodeListByExhibitionId(int exhibitionId);

    /**
     * 查询某次外展中指定类型的设备(按监测指标过滤设备)
     *
     * @param exhibitionId     外展ID
     * @param sensorPhysicalId 监测指标ID
     * @return 设备集合
     */
    public List<NodeInfoPO> findNodeListBySensorId(int exhibitionId, int sensorPhysicalId);

    /**
     * 查询设备实时数据信息
     *
     * @param nodeId 设备编号
     * @return RealTimeDataVO 实时数据VO 对象 此处未封装设备监测指标数据
     */
    public RealtimeDataVO findNodeInfo(String nodeId);

    /**
     * 获得设备监测指标实时数据
     *
     * @param nodeId               设备编号
     * @param sensorPhysicalIdList 筛选条件监测指标集合
     * @return List<SensorInfoVO> 监测指标数据信息列表
     */
    public List<DeviceDataVO> findNodeSensor(String nodeId,
                                             List<Integer> sensorPhysicalIdList);

}
