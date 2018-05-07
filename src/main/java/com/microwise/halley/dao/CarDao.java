package com.microwise.halley.dao;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.DevicePO;
import com.microwise.halley.bean.vo.CarVO;

import java.util.List;

/**
 * 车辆 Dao
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
public interface CarDao {

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
     */
    public CarPO findCarByDeviceId(int exhibitionId,String deviceId);

    /**
     * 根据位置点ID查询车辆信息
     *
     * @param locationId 位置点ID
     * @return 车辆信息
     */
    public CarPO findCarByLocationId(int exhibitionId, String locationId);

    /**
     * 添加车辆
     *
     * @param car 车辆实体类
     */
    public int saveCar(CarVO car);

    /**
     * 根据车辆ID，查询车辆上装的设备
     *
     * @param carId 车辆ID
     * @return Set<DevicePO> 设备集合
     * @author wang.geng
     * @date 2013-9-26
     */
    public List<DevicePO> findDevicesByCarId(int carId);
}
