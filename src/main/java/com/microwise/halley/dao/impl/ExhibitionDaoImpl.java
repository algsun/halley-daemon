package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.vo.ExhibitionVO;
import com.microwise.halley.bean.vo.RelicVO;
import com.microwise.halley.dao.ExhibitionDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外展 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
@Component
@Scope("prototype")
public class ExhibitionDaoImpl extends BaseDaoImpl implements ExhibitionDao {

    @Override
    public void syncExhibitions(String siteId) {
        getSqlSession().insert("ExhibitionDao.syncExhibitions", siteId);
    }

    @Override
    public List<ExhibitionVO> findExhibitionList(String siteId, int state) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("siteId", siteId);
        params.put("state", state);
        return getSqlSession().selectList("ExhibitionDao.findExhibitionList", params);
    }

    @Override
    public List<ExhibitionVO> findExhibitionListNotEnd() {
        return getSqlSession().selectList("ExhibitionDao.findExhibitionListNotEnd");
    }

    @Override
    public List<RelicVO> findExhibitionRelicList(int exhibitionId) {
        return getSqlSession().selectList("ExhibitionDao.findExhibitionRelicList", exhibitionId);
    }
}
