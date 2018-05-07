package com.microwise.halley.bean.vo;

import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.po.DevicePO;

import java.util.List;

/**
 * 车辆 VO
 *
 * @author li.jianfei
 * @date 2013-11-06
 */
public class CarVO extends CarPO {

    /**
     * 摄像机信息
     */
    private List<DevicePO> devicePOList;

    /**
     * 位置点信息
     */
    private List<LocationVO> locationVOs;


    /**
     * 集装箱集合
     */
    private List<PackingListVO> packingLists;

    public List<PackingListVO> getPackingLists() {
        return packingLists;
    }

    public void setPackingLists(List<PackingListVO> packingLists) {
        this.packingLists = packingLists;
    }

    public List<DevicePO> getDevicePOList() {
        return devicePOList;
    }

    public void setDevicePOList(List<DevicePO> devicePOList) {
        this.devicePOList = devicePOList;
    }

    public List<LocationVO> getLocationVOs() {
        return locationVOs;
    }

    public void setLocationVOs(List<LocationVO> locationVOs) {
        this.locationVOs = locationVOs;
    }
}
