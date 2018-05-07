package com.microwise.halley.bean.vo;

import java.util.Date;
import java.util.Map;

/**
 * 位置点近期数据
 *
 * @author liuzhu
 * @date 2014-7-3
 */
public class RecentDataVO {

    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 设备电压状态 0：正常 1：低电压 2：掉电 Y=x/10(实际电压，保留小数点1位) 其他情况参考协议内容
     */
    private float lowvoltage;

    /**
     * 设备工作状态 0：正常 1：异常 （是否按工作周期采集数据）
     */
    private int anomaly;

    /**
     * 时间戳
     */
    private Date stamp;

    /**
     * 位置点下监测指标的实时数据信息
     */
    private Map<Integer, LocationDataVO> sensorInfoMap;

    public float getLowvoltage() {
        return lowvoltage;
    }

    public void setLowvoltage(float lowvoltage) {
        this.lowvoltage = lowvoltage;
    }

    public int getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(int anomaly) {
        this.anomaly = anomaly;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public Map<Integer, LocationDataVO> getSensorInfoMap() {
        return sensorInfoMap;
    }

    public void setSensorInfoMap(Map<Integer, LocationDataVO> sensorInfoMap) {
        this.sensorInfoMap = sensorInfoMap;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
