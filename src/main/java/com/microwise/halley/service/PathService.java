package com.microwise.halley.service;

import com.microwise.halley.bean.vo.AlarmConfigsVO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.PathPointsVO;
import com.microwise.halley.bean.vo.RealtimeDataVO;

import java.util.List;
import java.util.Map;

/**
 * 路线服务
 *
 * @author wang.geng
 * @date 13-11-12 下午4:11
 */
public interface PathService {
    /**
     * 根据外展ID查询预设路线的拐点集合
     *
     * @param exhibitionId 外展ID
     * @return 拐点集合
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
