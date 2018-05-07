package com.microwise.halley.service;

import com.microwise.halley.bean.po.AlarmPO;

/**
 * 报警记录 Service
 *
 * @author li.jianfei
 * @date 2014-05-19
 */
public interface AlarmService {
    /**
     * 添加报警记录
     *
     * @param alarm 报警记录对象
     * @return
     */
    public int save(AlarmPO alarm);
}
