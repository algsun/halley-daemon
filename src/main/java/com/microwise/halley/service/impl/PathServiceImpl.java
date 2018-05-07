package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.DevicePO;
import com.microwise.halley.bean.vo.*;
import com.microwise.halley.dao.CarDao;
import com.microwise.halley.dao.DeviceDao;
import com.microwise.halley.dao.PathDao;
import com.microwise.halley.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路线Service实现
 *
 * @author wang.geng
 * @date 13-11-12 下午4:15
 */
@Service
@Scope("prototype")
@Transactional
public class PathServiceImpl implements PathService {

    /**
     * 设备检测指标ID:经度
     */
    public static final int SENSORPHYSICALID_LONGITUDE = 12287;
    /**
     * 设备检测指标ID:纬度
     */
    public static final int SENSORPHYSICALID_LATITUDE = 12286;

    /**
     * 设备检测指标常量:经度
     */
    public static final String LONGITUDE = "longitude";

    /**
     * 设备检测指标常量:纬度
     */
    public static final String LATITUDE = "latitude";

    @Autowired
    private PathDao pathDao;
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private CarDao carDao;

    @Override
    public List<PathPointsVO> findExhibitionPathPoints(int exhibitionId) {
        return pathDao.findExhibitionPathPoints(exhibitionId);
    }

    @Override
    public AlarmConfigsVO findAlarmConfigs(int exhibitionId) {
        return pathDao.findAlarmConfigs(exhibitionId);
    }
}
