package com.microwise.halley.bean.vo;

import java.util.Date;
import java.util.Map;

/**
 * 实时数据vo对象
 *
 * @author zhangpeng
 * @date 2013-1-18
 */
public class RealtimeDataVO {

    /**
     * 设备编号
     */
    private String nodeId;

    /**
     * 设备中文名称
     */
    private String nodeName;

    /**
     * 设备隶属的区域编号
     */
    private String zoneId;

    /**
     * 设备隶属的区域名称
     */
    private String zoneName;

    /**
     * 设备电压状态 0：正常 1：低电压 2：掉电 Y=x/10(实际电压，保留小数点1位) 其他情况参考协议内容
     */
    private float lowVoltage;

    /**
     * 设备工作状态 0：正常 1：异常 （是否按工作周期采集数据）
     */
    private int anomaly;

    /**
     * 设备工作模式 0：正常模式 1：巡检模式
     */
    private int deviceMode;

    /**
     * 设备接收信号强度
     */
    private Integer rssi;

    /**
     * 设备链路质量
     */
    private Integer lqi;

    /**
     * 设备实时数据采样时间 采用一组数据中时间最大值
     */
    private Date stamp;

    /**
     * 设备下监测指标的实时数据信息
     */
    private Map<Integer, DeviceDataVO> sensorInfoMap;

    /**
     * 位置点下监测指标的实时数据信息
     */
    private Map<Integer, LocationDataVO> locationSensorInfoMap;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public float getLowVoltage() {
        return lowVoltage;
    }

    public void setLowVoltage(float lowVoltage) {
        this.lowVoltage = lowVoltage;
    }

    public int getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(int anomaly) {
        this.anomaly = anomaly;
    }

    public int getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(int deviceMode) {
        this.deviceMode = deviceMode;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Integer getLqi() {
        return lqi;
    }

    public void setLqi(Integer lqi) {
        this.lqi = lqi;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public Map<Integer, DeviceDataVO> getSensorInfoMap() {
        return sensorInfoMap;
    }

    public void setSensorInfoMap(Map<Integer, DeviceDataVO> sensorInfoMap) {
        this.sensorInfoMap = sensorInfoMap;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return "RealTimeDataVO{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", zoneId='" + zoneId + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", lowvoltage=" + lowVoltage +
                ", anomaly=" + anomaly +
                ", deviceMode=" + deviceMode +
                ", rssi=" + rssi +
                ", lqi=" + lqi +
                ", stamp=" + stamp +
                ", sensorinfoMap=" + sensorInfoMap +
                '}';
    }

    public Map<Integer, LocationDataVO> getLocationSensorInfoMap() {
        return locationSensorInfoMap;
    }

    public void setLocationSensorInfoMap(Map<Integer, LocationDataVO> locationSensorInfoMap) {
        this.locationSensorInfoMap = locationSensorInfoMap;
    }
}
