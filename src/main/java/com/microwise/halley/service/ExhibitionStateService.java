package com.microwise.halley.service;

import com.microwise.halley.bean.po.PathPO;
import com.microwise.halley.bean.vo.ExhibitionStateVO;

import java.util.List;

/**
 * 外展状态 Service
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
public interface ExhibitionStateService {

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
     * @param exhibitionId 当前目的地Id
     * @return 状态的历史操作集合
     */
    public List<ExhibitionStateVO> getHistoryState(int exhibitionId);

    /**
     * 获取出发地
     *
     * @author 王耕
     * @date 2015-8-11
     *
     * @param exhibitionId 外展ID
     * @return PathPO
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
    public PathPO getNearBy(int exhibitionId, double longitude, double latitude);
}
