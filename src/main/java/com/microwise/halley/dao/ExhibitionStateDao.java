package com.microwise.halley.dao;

import com.microwise.halley.bean.po.PathPO;
import com.microwise.halley.bean.vo.ExhibitionStateVO;

import java.util.List;

/**
 * 外展状态 Dao
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
public interface ExhibitionStateDao {

    /**
     * 获取外展的当前状态
     *
     * @param exhibitionId 外展ID
     * @return 外展状态PO
     */
    public ExhibitionStateVO findCurrentState(int exhibitionId);

    /**
     * 获取某次外展的历史操作
     *
     * @author 王耕
     * @date 2015-8-11
     *
     * @param exhibitionId 外展ID
     * @return 状态的历史操作集合
     */
    public List<ExhibitionStateVO> getHistoryState(int exhibitionId);

    /**
     * 查找所有路径
     *
     * @author 王耕
     * @date 2015-8-11
     *
     * @param exhibitionId 外展Id
     * @return 所有路径集合
     */
    public List<PathPO> getALLPathPO(int exhibitionId);

    /**
     * 获取出发地
     *
     * @author 王耕
     * @date 2015-8-11
     *
     * @param exhibitionId 外展ID
     * @return PathPO   目的地对象
     */
    public PathPO getStartDestination(int exhibitionId);

    /**
     * 查询外展线路离某点最近的点
     *
     * @author 王耕
     * @date 2015-8-11
     *
     * @param exhibitionId
     * @param longitude
     * @param latitude
     * @return
     */
    public PathPO getNearBy(int exhibitionId,double longitude,double latitude);
}

