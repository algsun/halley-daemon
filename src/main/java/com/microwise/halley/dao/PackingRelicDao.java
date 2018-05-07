package com.microwise.halley.dao;

import com.microwise.halley.bean.vo.PackingRelicVO;

/**
 * 装箱文物 Dao
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
public interface PackingRelicDao {

    /**
     * 添加装箱文物信息
     * @param packingRelic 装箱文物
     * @return 记录号
     */
    public int savePackingRelic(PackingRelicVO packingRelic);
}
