package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.DevicePO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.dao.CarDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
@Component
@Scope("prototype")
public class CarDaoImpl extends BaseDaoImpl implements CarDao {
    @Override
    public List<CarPO> findCarsByExhibitionId(int exhibitionId) {
        return getSqlSession().selectList(
                "CarDao.findCarsByExhibitionId", exhibitionId);
    }

    @Override
    public CarPO findCarByDeviceId(int exhibitionId, String deviceId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exhibitionId", exhibitionId);
        params.put("deviceId", deviceId);
        return getSqlSession().selectOne("CarDao.findCarByDeviceId", params);
    }

    @Override
    public int saveCar(CarVO car) {
        getSqlSession().insert("CarDao.saveCar", car);
        return car.getId();
    }

    @Override
    public List<DevicePO> findDevicesByCarId(int carId) {
        return getSqlSession().selectList(
                "halley.mybatis.CarDao.findDevicesByCarId", carId
        );
    }

    @Override
    public CarPO findCarByLocationId(int exhibitionId, String locationId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exhibitionId", exhibitionId);
        params.put("locationId", locationId);
        return getSqlSession().selectOne("CarDao.findCarByLocationId", params);
    }
}
