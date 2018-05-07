package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.dao.CarDao;
import com.microwise.halley.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 车辆 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
@Service
@Scope("prototype")
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<CarPO> findCarsByExhibitionId(int exhibitionId) {
        return carDao.findCarsByExhibitionId(exhibitionId);
    }

    @Override
    public CarPO findCarByDeviceId(int exhibitionId,String deviceId) {
        return carDao.findCarByDeviceId(exhibitionId,deviceId);
    }

    @Override
    public CarPO findCarByLocationId(int exhibitionId, String locationId) {
        return  carDao.findCarByLocationId(exhibitionId,locationId);
    }
}
