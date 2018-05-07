package com.microwise.halley.dao;

import com.microwise.halley.bean.po.AlarmPO;

/**
 * 外展报警记录 Dao
 *
 * @author li.jianfei
 * @date 2014-05-19
 */
public interface AlarmDao {
    /**
     * 添加报警记录
     *
     * @param alarm 报警记录对象
     * @return
     */
    public int save(AlarmPO alarm);
}
