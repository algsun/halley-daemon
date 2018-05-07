package com.microwise.halley.bean.vo;

import java.util.Date;
import java.util.Map;

/**
 * 位置点数据VO对象（封装实时数据/历史数据监测指标对应值）
 *
 * @author xuyuexi
 * @date 2014-8-14
 */
public class LocationDataVO {

    /**
     * id
     */
    private int id;

    /**
     * 设备ID
     */
    private String nodeId;

    private String locationName;

    /**
     * 监测指标标识
     */
    private int sensorPhysicalid;

    /**
     * 监测指标中文名称
     */
    private String cnName;

    /**
     * 监测指标英文名称
     */
    private String enName;

    /**
     * 监测指标单位
     */
    private String units;

    /**
     * 监测指标值
     */
    private String sensorPhysicalValue;

    /**
     * 监测指标值状态 0：采样失败 0xFFFF为采样失败 1：采样正常 2：低于低阈值 3：超过高阈值 4：空数据（前台暂不处理）
     */
    private int state;

    /**
     * 当前监测指标的采样时间
     */
    private Date stamp;

    /**
     * 监测指标类型 0: 普通类型 1：风向类 类型
     */
    private int showType;

    /**
     * 区域信息id
     */
    private String zoneId;

    /**
     * 节点类型
     */
    private int nodeType;

    private boolean isNode;

    /**
     * 监测指标的最大值
     */
    private String bigValue;

    /**
     * 监测指标的最小值
     */
    private String smallValue;

    /**
     * 监测值 map集合
     */
    private Map<Long, LocationDataVO> sensorPhysicalValueMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorPhysicalid() {
        return sensorPhysicalid;
    }

    public void setSensorPhysicalid(int sensorPhysicalid) {
        this.sensorPhysicalid = sensorPhysicalid;
    }

    public String getSensorPhysicalValue() {
        return sensorPhysicalValue;
    }

    public void setSensorPhysicalValue(String sensorPhysicalValue) {
        this.sensorPhysicalValue = sensorPhysicalValue;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Map<Long, LocationDataVO> getSensorPhysicalValueMap() {
        return sensorPhysicalValueMap;
    }

    public void setSensorPhysicalValueMap(Map<Long, LocationDataVO> sensorPhysicalValueMap) {
        this.sensorPhysicalValueMap = sensorPhysicalValueMap;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean isNode() {
        return isNode;
    }

    public String getBigValue() {
        return bigValue;
    }

    public void setBigValue(String bigValue) {
        this.bigValue = bigValue;
    }

    public String getSmallValue() {
        return smallValue;
    }

    public void setSmallValue(String smallValue) {
        this.smallValue = smallValue;
    }

    public void setNode(boolean isNode) {
        this.isNode = isNode;
    }

    @Override
    public String toString() {
        return "DeviceDataVO [sensorPhysicalid=" + sensorPhysicalid
                + ", sensorPhysicalValue=" + sensorPhysicalValue + ", state="
                + state + ", stamp=" + stamp + ", showType=" + showType + "]";
    }

}
