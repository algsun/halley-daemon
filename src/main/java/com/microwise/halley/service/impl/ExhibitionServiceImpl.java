package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.ExhibitionVO;
import com.microwise.halley.bean.vo.PackingListVO;
import com.microwise.halley.bean.vo.PackingRelicVO;
import com.microwise.halley.dao.CarDao;
import com.microwise.halley.dao.ExhibitionDao;
import com.microwise.halley.dao.PackingListDao;
import com.microwise.halley.dao.PackingRelicDao;
import com.microwise.halley.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 外展记录 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
@Service
@Scope("prototype")
@Transactional
public class ExhibitionServiceImpl implements ExhibitionService {

    @Autowired
    private ExhibitionDao exhibitionDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private PackingListDao packingListDao;

    @Autowired
    private PackingRelicDao packingRelicDao;

    @Override
    public List<ExhibitionVO> findExhibitionList(String siteId) {

        exhibitionDao.syncExhibitions(siteId);
        // 只查询准备中的外展记录
        List<ExhibitionVO> exhibitionList = exhibitionDao.findExhibitionList(siteId, 0);

        for (ExhibitionVO exhibition : exhibitionList) {
            int exhibitionId = exhibition.getExhibitionId();
            // 设置外展车辆
            exhibition.setCarList(carDao.findCarsByExhibitionId(exhibitionId));
            // 设置外展文物
            exhibition.setRelicList(exhibitionDao.findExhibitionRelicList(exhibition.getExhibitionId()));
        }

        return exhibitionList;
    }

    @Override
    public List<ExhibitionVO> findExhibitionListNotEnd() {
        return exhibitionDao.findExhibitionListNotEnd();
    }

    @Override
    public void uploadExhibitionPackingList(int exhibitionId, List<CarVO> carList) {

        // 查询对比车辆信息：数据库不存在车辆记录时添加
        List<CarPO> existCarList = carDao.findCarsByExhibitionId(exhibitionId);

        // 已存在的车辆添加装箱单，并删除车辆信息
        Iterator<CarVO> iterator = carList.iterator();
        while (iterator.hasNext()) {
            CarVO car = iterator.next();
            for (CarPO existCar : existCarList) {
                // 车辆相同(外展ID、车牌号同时相同)
                if (car.getExhibitionId() == existCar.getExhibitionId()
                        && car.getPlateNumber().equals(existCar.getPlateNumber())) {
                    addPackingList(existCar.getId(), car.getPackingLists());

                    // 删除已存在车辆信息
                    iterator.remove();
                }
            }
        }

        // 不存在的车辆，添加车辆并添加装箱单
        for (CarVO car : carList) {
            car.setExhibitionId(exhibitionId);
            addPackingList(carDao.saveCar(car), car.getPackingLists());
        }

    }

    private void addPackingList(int carId, List<PackingListVO> packingLists) {
        // 添加装箱单信息
        for (PackingListVO packingList : packingLists) {
            packingList.setCarId(carId);
            int packingId = packingListDao.savePackingList(packingList);

            for (PackingRelicVO packingRelic : packingList.getRelicList()) {
                packingRelic.setPackingId(packingId);
                packingRelicDao.savePackingRelic(packingRelic);
            }
        }
    }
}
