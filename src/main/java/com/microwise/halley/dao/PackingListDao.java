package com.microwise.halley.dao;

import com.microwise.halley.bean.vo.PackingListVO;

/**
 * 集装箱 Dao
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
public interface PackingListDao {

    /**
     * 添加车辆集装箱信息
     *
     * @param packingList 集装箱信息
     * @return 集装箱ID
     */
    public int savePackingList(PackingListVO packingList);
}
