package com.microwise.halley.bean.vo;

/**
 * 报警业务配置实体
 *
 * @author wang.geng
 * @date 13-11-13 上午9:30
 */
public class AlarmConfigsVO {
    /**
     * 配置ID
     */
    private int id;

    /**
     *外展ID
     */
    private int exhibitionId;

    /**
     * 报警范围，单位公里
     */
    private int alarmRange;

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

    public int getAlarmRange() {
        return alarmRange;
    }

    public void setAlarmRange(int alarmRange) {
        this.alarmRange = alarmRange;
    }
}
