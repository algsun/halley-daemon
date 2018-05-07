package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.NodeInfoPO;
import com.microwise.halley.bean.vo.DeviceDataVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;
import com.microwise.halley.dao.DeviceDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-11
 */
@Component
@Scope("prototype")
public class DeviceDaoImpl extends BaseDaoImpl implements DeviceDao {
    @Override
    public List<NodeInfoPO> findNodeListByExhibitionId(int exhibitionId) {
        return getSqlSession().selectList("DeviceDao.findNodeListByExhibitionId", exhibitionId);
    }

    @Override
    public List<NodeInfoPO> findNodeListBySensorId(int exhibitionId, int sensorPhysicalId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exhibitionId", exhibitionId);
        params.put("sensorPhysicalId", sensorPhysicalId);
        return getSqlSession().selectList("DeviceDao.findNodeListBySensorId", params);
    }

    @Override
    public RealtimeDataVO findNodeInfo(String nodeId) {
        return getSqlSession().selectOne("DeviceDao.findNodeInfo", nodeId);
    }

    @Override
    public List<DeviceDataVO> findNodeSensor(String nodeId, List<Integer> sensorPhysicalIdList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nodeId", nodeId);
        params.put("sensorPhysicalIdList", sensorPhysicalIdList);
        return getSqlSession().selectList("DeviceDao.findNodeSensor", params);
    }
}
