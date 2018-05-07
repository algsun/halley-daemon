package com.microwise.halley.action;

import com.bastengao.struts2.freeroute.Results;
import com.bastengao.struts2.freeroute.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microwise.halley.bean.po.CarPO;
import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.ExhibitionVO;
import com.microwise.halley.service.CarService;
import com.microwise.halley.service.ExhibitionService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外展点交 http 接口
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
@Component
@Scope("prototype")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private CarService carService;

    // Input
    /**
     * 站点ID
     */
    private String siteId;

    /**
     * 外展ID
     */
    private int exhibitionId;

    /**
     * 装箱单 Json 字符串
     */
    private String packingListJson;

    @Route(value = "/getExhibitionList", params = {"siteId"})
    public String getExhibitionList() {
        List<ExhibitionVO> exhibitionList = null;
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            exhibitionList = exhibitionService.findExhibitionList(siteId);
            data.put("success", true);
        } catch (Exception e) {
            data.put("success", false);
            e.printStackTrace();
        }
        data.put("data", exhibitionList);
        ActionContext.getContext().put("data", data);
        return Results.json().root("data").done();
    }

    @Route(value = "/uploadExhibitionPackingList", params = {"exhibitionId", "packingListJson"})
    public String uploadExhibitionPackingList() {
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            Type type = new TypeToken<List<CarVO>>() {
            }.getType();
            List<CarVO> carList = new Gson().fromJson(packingListJson, type);
            exhibitionService.uploadExhibitionPackingList(exhibitionId, carList);
            data.put("success", true);
        } catch (Exception e) {
            data.put("success", false);
            e.printStackTrace();
        }
        ActionContext.getContext().put("data", data);

        return Results.json().root("data").done();
    }

    @Route(value = "/getCarList", params = {"exhibitionId"})
    public String getCarList() {
        List<CarPO> carList = null;
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            carList = carService.findCarsByExhibitionId(exhibitionId);
            data.put("success", true);
        } catch (Exception e) {
            data.put("success", false);
            e.printStackTrace();
        }
        data.put("data", carList);
        ActionContext.getContext().put("data", data);
        return Results.json().root("data").done();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getPackingListJson() {
        return packingListJson;
    }

    public void setPackingListJson(String packingListJson) {
        this.packingListJson = packingListJson;
    }
}
