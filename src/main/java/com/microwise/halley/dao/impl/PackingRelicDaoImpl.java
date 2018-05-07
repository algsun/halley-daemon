package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.vo.PackingRelicVO;
import com.microwise.halley.dao.PackingRelicDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 装箱文物 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
@Component
@Scope("prototype")
public class PackingRelicDaoImpl extends BaseDaoImpl implements PackingRelicDao {
    @Override
    public int savePackingRelic(PackingRelicVO packingRelic) {
        getSqlSession().insert("PackingRelicDao.savePackingRelic", packingRelic);
        return packingRelic.getId();
    }
}
