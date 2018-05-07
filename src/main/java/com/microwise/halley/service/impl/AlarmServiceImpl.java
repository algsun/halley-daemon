package com.microwise.halley.service.impl;

import com.microwise.halley.bean.po.AlarmPO;
import com.microwise.halley.dao.AlarmDao;
import com.microwise.halley.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AlarmService 实现
 *
 * @author li.jianfei
 * @date 2014-05-19
 */
@Service
@Scope("prototype")
@Transactional
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    @Override
    public int save(AlarmPO alarm) {
        return alarmDao.save(alarm);
    }
}
