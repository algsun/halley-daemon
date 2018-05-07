package com.microwise.halley.bean.po;

import java.util.Date;

/**
 * 历史线路 PO
 *
 * @author li.jianfei
 * @date 2013-11-14
 */
public class RouteHistoryPO {

    /**
     * 历史线路ID
     */
    private int id;

    /**
     * 车辆ID
     */
    private int carId;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 上传时间
     */
    private Date time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
