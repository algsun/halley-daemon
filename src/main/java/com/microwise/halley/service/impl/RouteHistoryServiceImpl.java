package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.RouteHistoryPO;
import com.microwise.halley.dao.RouteHistoryDao;
import com.microwise.halley.service.RouteHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 历史线路 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-14
 */
@Service
@Scope("prototype")
@Transactional
public class RouteHistoryServiceImpl implements RouteHistoryService {

    @Autowired
    private RouteHistoryDao routeHistoryDao;

    @Override
    public boolean exists(RouteHistoryPO routeHistory) {
        return routeHistoryDao.findRouteHistory(routeHistory.getCarId(), routeHistory.getTime()) != null;
    }

    @Override
    public int saveRouteHistory(RouteHistoryPO routeHistory) {
        return routeHistoryDao.saveRouteHistory(routeHistory);
    }

    @Override
    public List<RouteHistoryPO> findRouteHistory(int carId) {
        return routeHistoryDao.findRouteHistory(carId);
    }

    @Override
    public int findLastConversionId(String locationId, int carId, int exhibitionId) {
        return routeHistoryDao.findLastConversionId(locationId, carId, exhibitionId);
    }
}
