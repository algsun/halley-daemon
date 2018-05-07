package com.microwise.halley.bean.vo;

import com.microwise.halley.bean.po.CarPO;

import java.util.Date;
import java.util.List;

/**
 * 外展记录 VO
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
public class ExhibitionVO {

    /**
     * 外展ID
     */
    private int exhibitionId;

    /**
     * 事件ID
     */
    private String outEventId;

    /**
     * 提用目的
     */
    private String useFor;

    /**
     * 预计开始时间
     */
    private Date estimatedBeginTime;

    /**
     * 预计结束时间
     */
    private Date estimatedEndTime;

    /**
     * 申请人/单位
     */
    private String applicant;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 外展车辆
     */
    private List<CarPO> carList;

    /**
     * 外展文物清单
     */
    private List<RelicVO> relicList;

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getOutEventId() {
        return outEventId;
    }

    public void setOutEventId(String outEventId) {
        this.outEventId = outEventId;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public Date getEstimatedBeginTime() {
        return estimatedBeginTime;
    }

    public void setEstimatedBeginTime(Date estimatedBeginTime) {
        this.estimatedBeginTime = estimatedBeginTime;
    }

    public Date getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(Date estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
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

    public List<CarPO> getCarList() {
        return carList;
    }

    public void setCarList(List<CarPO> carList) {
        this.carList = carList;
    }

    public List<RelicVO> getRelicList() {
        return relicList;
    }

    public void setRelicList(List<RelicVO> relicList) {
        this.relicList = relicList;
    }
}
