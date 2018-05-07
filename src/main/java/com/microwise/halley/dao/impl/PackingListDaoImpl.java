package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.vo.PackingListVO;
import com.microwise.halley.dao.PackingListDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 集装箱 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
@Component
@Scope("prototype")
public class PackingListDaoImpl extends BaseDaoImpl implements PackingListDao {

    @Override
    public int savePackingList(PackingListVO packingList) {
        getSqlSession().insert("PackingListDao.savePackingList", packingList);
        return packingList.getId();
    }
}
