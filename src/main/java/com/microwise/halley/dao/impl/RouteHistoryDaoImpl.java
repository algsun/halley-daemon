package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.RouteHistoryPO;
import com.microwise.halley.dao.RouteHistoryDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 历史线路 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-14
 */
@Component
@Scope("prototype")
public class RouteHistoryDaoImpl extends BaseDaoImpl implements RouteHistoryDao {

    @Override
    public RouteHistoryPO findRouteHistory(int carId, Date time) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carId", carId);
        params.put("time", time);
        return getSqlSession().selectOne("RouteHistoryDao.findRouteHistory", params);
    }

    @Override
    public int saveRouteHistory(RouteHistoryPO routeHistory) {
        return getSqlSession().insert("RouteHistoryDao.saveRouteHistory", routeHistory);
    }

    @Override
    public List<RouteHistoryPO> findRouteHistory(int carId) {
        return getSqlSession().selectList("RouteHistoryDao.findRouteHistoryList", carId);
    }

    @Override
    public int findLastConversionId(String locationId, int carId, int exhibitionId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("locationId", locationId);
        params.put("carId", carId);
        params.put("exhibitionId",exhibitionId);
        int count = getSqlSession().selectOne("RouteHistoryDao.findLastConversionIdCount", params);
        if (count == 0) {
            int countHistory =getSqlSession().selectOne("RouteHistoryDao.findLastConversionId2Count", params);
            if(countHistory == 0){
                return 0;
            }else{
                return getSqlSession().selectOne("RouteHistoryDao.findLastConversionId2", params);
            }
        } else {
            return getSqlSession().selectOne("RouteHistoryDao.findLastConversionId", params);
        }

    }
}
