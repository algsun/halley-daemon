package com.microwise.halley.dao;

import com.microwise.halley.bean.vo.AlarmConfigsVO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.PathPointsVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;

import java.util.List;
import java.util.Map;

/**
 * 路线Dao
 *
 * @author wang.geng
 * @date 13-11-12 下午3:51
 */
public interface PathDao {
    /**
     * 根据外展ID获取预设路线的拐点
     *
     * @param exhibitionId 外展ID
     * @return 拐点实体集合
     */
    public List<PathPointsVO> findExhibitionPathPoints(int exhibitionId);

    /**
     * 根据外展ID，查询报警配置信息
     *
     * @param exhibitionId 外展ID
     * @return 配置信息
     */
    public AlarmConfigsVO findAlarmConfigs(int exhibitionId);


}
