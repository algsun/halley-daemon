package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.AlarmPO;
import com.microwise.halley.dao.AlarmDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * AlarmDao 实现
 *
 * @author li.jianfei
 * @date 2014-05-19
 */
@Component
@Scope("prototype")
public class AlarmDaoImpl extends BaseDaoImpl implements AlarmDao {
    @Override
    public int save(AlarmPO alarm) {
        getSqlSession().insert("AlarmDao.save", alarm);
        return alarm.getId();
    }
}
