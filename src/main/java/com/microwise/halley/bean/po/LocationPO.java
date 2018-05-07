package com.microwise.halley.bean.po;

import java.util.Date;

/**
 * 位置点实体类
 *
 * @author xuyuexi
 * @date 2014-08-14
 */
public class LocationPO {

    /**
     * 位置点ID
     */
    private String id;

    /**
     * 位置点名称
     */
    private String locationName;

    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 区域ID
     */
    private String zoneId;

    /**
     * 站点编号
     */
    private String siteId;


    /**
     * 位置点创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
