package com.microwise.halley.service;

import com.microwise.halley.bean.po.RouteHistoryPO;

import java.util.List;

/**
 * 历史线路 Service
 *
 * @author li.jianfei
 * @date 2013-11-14
 */
public interface RouteHistoryService {

    /**
     * 判断是否存在
     *
     * @param routeHistory
     * @return
     */
    public boolean exists(RouteHistoryPO routeHistory);

    /**
     * 保存历史线路 GPS 点信息
     *
     * @param routeHistory 历史线路点
     * @return 自增ID
     */
    public int saveRouteHistory(RouteHistoryPO routeHistory);

    /**
     * 查询某辆车的历史路线数据
     *
     * @param carId 车辆ID
     * @return 路线集合
     * @author 王耕
     * @date 2015-8-13
     */
    public List<RouteHistoryPO> findRouteHistory(int carId);

    /**
     * 查询最后一条转换数据的Id
     *
     * @param locationId
     * @param carId
     * @return
     */
    public int findLastConversionId(String locationId, int carId, int exhibitionId);
}
