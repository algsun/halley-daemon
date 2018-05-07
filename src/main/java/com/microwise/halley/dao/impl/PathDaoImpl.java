package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.vo.AlarmConfigsVO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.PathPointsVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;
import com.microwise.halley.dao.PathDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路线Dao 实现
 *
 * @author wang.geng
 * @date 13-11-12 下午4:00
 */
@Component
@Scope("prototype")
public class PathDaoImpl extends BaseDaoImpl implements PathDao{
    @Override
    public List<PathPointsVO> findExhibitionPathPoints(int exhibitionId) {
        return getSqlSession().selectList(
                "PathDao.findExhibitionPathPoints",exhibitionId);
    }

    @Override
    public AlarmConfigsVO findAlarmConfigs(int exhibitionId){
        return getSqlSession().selectOne("PathDao.findAlarmConfigs",exhibitionId);
    }

}
