package com.microwise.halley.bean.vo;


import com.microwise.halley.bean.po.DevicePO;
import com.microwise.halley.bean.po.LocationPO;

import java.util.List;

/**
 * 位置点 业务类
 *
 * @author xuyuexi
 * @date 2014-08-14
 */
public class LocationVO extends LocationPO {

    /**
     * 是否区域的直接位置点
     */
    private boolean localLocation;

    /**
     * 在父区域平面部署图的X坐标
     */
    private float coordinateX = -1;

    /**
     * 在父区域平面部署图的Y坐标
     */
    private float coordinateY = -1;
    /**
     * 位置点绑定设备(当前)
     */
    private DevicePO device;

    /**
     * 位置点图标
     */
    private String locationIcon;

    /**
     * 位置点的实时数据
     */
    private RealtimeDataVO realtimeDataVO;

    private List<Integer> sensorIdList;

    public boolean isLocalLocation() {
        return localLocation;
    }

    public void setLocalLocation(boolean localLocation) {
        this.localLocation = localLocation;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public List<Integer> getSensorIdList() {
        return sensorIdList;
    }

    public String getLocationIcon() {
        return locationIcon;
    }

    public void setLocationIcon(String locationIcon) {
        this.locationIcon = locationIcon;
    }

    public void setSensorIdList(List<Integer> sensorIdList) {
        this.sensorIdList = sensorIdList;
    }

    public RealtimeDataVO getRealtimeDataVO() {
        return realtimeDataVO;
    }

    public void setRealtimeDataVO(RealtimeDataVO realtimeDataVO) {
        this.realtimeDataVO = realtimeDataVO;
    }

    public DevicePO getDevice() {
        return device;
    }

    public void setDevice(DevicePO device) {
        this.device = device;
    }
}
