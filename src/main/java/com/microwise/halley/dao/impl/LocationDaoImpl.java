package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.LocationPO;
import com.microwise.halley.bean.vo.LocationDataVO;
import com.microwise.halley.bean.vo.LocationVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;
import com.microwise.halley.bean.vo.RecentDataVO;
import com.microwise.halley.dao.LocationDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 位置点 Dao 实现
 *
 * @author xuyuexi
 * @date 2014-08-14
 */
@Component
@Scope("prototype")
public class LocationDaoImpl extends BaseDaoImpl implements LocationDao {

    @Override
    public List<LocationVO> findLocationsByExhibitionId(int exhibitionId) {
        return getSqlSession().selectList("LocationDao.findLocationsByExhibitionId", exhibitionId);
    }

    @Override
    public List<LocationVO> findLocationsBySensorId(int exhibitionId, int sensorPhysicalId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exhibitionId", exhibitionId);
        map.put("sensorPhysicalId", sensorPhysicalId);
        return getSqlSession().selectList("LocationDao.findLocationsBySensorId", map);
    }

    @Override
    public RealtimeDataVO findRealtimeData(String locationId) {
        return getSqlSession().selectOne("LocationDao.findLocationData", locationId);
    }

    @Override
    public List<LocationDataVO> findLocationDataVO(String locationId, List<Integer> sensorPhysicalIdList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nodeId", locationId);
        paramMap.put("sensorPhysicalIdList", sensorPhysicalIdList);
        return getSqlSession().selectList("LocationDao.findLocationDataVO", paramMap);
    }

    @Override
    public LocationVO findLocationById(String locationId) {
        return getSqlSession().selectOne("LocationDao.findLocationById", locationId);
    }

    @Override
    public List<LocationDataVO> findHistoryDataList(String locationId, int id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("locationId", locationId);
        paramMap.put("id", id);
        return getSqlSession().selectList(
                "LocationDao.findHistoryDataList", paramMap);
    }

    @Override
    public List<LocationVO> findLocationsByCarId(int carId, int sensorPhysicalId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("carId", carId);
        map.put("sensorPhysicalId", sensorPhysicalId);
        return getSqlSession().selectList("LocationDao.findLocationsByCarId", map);
    }
}
