package com.microwise.halley.bean.vo;

import com.microwise.halley.bean.po.PathPO;

import java.util.Date;

/**
 * 外展状态 VO
 *
 * @author xu.yuexi
 * @date 2013-10-23
 */
public class ExhibitionStateVO {

    /**
     * 记录ID
     */
    private int id;

    /**
     * 外展ID
     */
    private int exhibitionId;

    /**
     * 状态
     */
    private int state;

    /**
     * 状态实际开始时间
     */
    private Date beginTime;

    /**
     * 状态实际结束时间
     */
    private Date endTime;
    /**
     * 操作人Id
     */
    private int operator;

    private PathPO startPathPO;

    /**
     * 下一个目的地
     */
    private PathPO endPathPO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public PathPO getStartPathPO() {
        return startPathPO;
    }

    public void setStartPathPO(PathPO startPathPO) {
        this.startPathPO = startPathPO;
    }

    public PathPO getEndPathPO() {
        return endPathPO;
    }

    public void setEndPathPO(PathPO endPathPO) {
        this.endPathPO = endPathPO;
    }
}
