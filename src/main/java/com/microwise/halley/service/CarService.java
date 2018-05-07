package com.microwise.halley.service;

import com.microwise.halley.bean.po.CarPO;

import java.util.List;

/**
 * 车辆 Service
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
public interface CarService {

    /**
     * 根据外展ID，查询此次外展的车辆
     *
     * @param exhibitionId 外展ID
     * @return List<CarPO> 车辆集合
     */
    public List<CarPO> findCarsByExhibitionId(int exhibitionId);

    /**
     * 根据设备ID查询车辆信息
     *
     * @param deviceId 设备ID
     * @return 车辆信息
     * @deprecated
     */
    public CarPO findCarByDeviceId(int exhibitionId, String deviceId);

    /**
     * 根据位置点ID查询车辆信息
     *
     * @param locationId 位置点ID
     * @return 车辆信息
     */
    public CarPO findCarByLocationId(int exhibitionId, String locationId);
}
