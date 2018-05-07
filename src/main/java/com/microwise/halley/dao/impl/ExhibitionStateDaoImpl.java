package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.po.PathPO;
import com.microwise.halley.bean.vo.ExhibitionStateVO;
import com.microwise.halley.dao.ExhibitionStateDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外展状态 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
@Component
@Scope("prototype")
public class ExhibitionStateDaoImpl extends BaseDaoImpl implements ExhibitionStateDao {
    @Override
    public ExhibitionStateVO findCurrentState(int exhibitionId) {
        return getSqlSession().selectOne("ExhibitionStateDao.findCurrentState", exhibitionId);
    }

    @Override
    public List<ExhibitionStateVO> getHistoryState(int exhibitionId) {
        return getSqlSession().selectList("ExhibitionStateDao.getHistoryState", exhibitionId);
    }

    @Override
    public List<PathPO> getALLPathPO(int ExhibitionId) {
        return getSqlSession().selectList("ExhibitionStateDao.getALLPathPO", ExhibitionId);
    }

    @Override
    public PathPO getStartDestination(int exhibitionId) {
        return getSqlSession().selectOne("ExhibitionStateDao.getStartDestination", exhibitionId);
    }

    @Override
    public PathPO getNearBy(int exhibitionId,double longitude,double latitude){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("longitude",longitude);
        paramMap.put("latitude",latitude);
        paramMap.put("exhibitionId",exhibitionId);
        return  getSqlSession().selectOne("ExhibitionStateDao.getNearBy", paramMap);
    }
}
