package com.microwise.halley.bean.vo;

import java.util.Date;

/**
 * 文物 PO
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
public class RelicVO {

    /**
     * 文物ID
     */
    private int id;

    /**
     * 总登记号
     */
    private String totalCode;

    /**
     * 标签号
     */
    private String tagCode;

    /**
     * 编目号
     */
    private String catalogCode;

    /**
     * 分类号
     */
    private String typeCode;

    /**
     * 文物名称
     */
    private String name;

    /**
     * 时代
     */
    private String era;

    /**
     * 级别
     */
    private String level;

    /**
     * 质地
     */
    private String texture;

    /**
     * 件数
     */
    private int count;

    /**
     * 出库时间
     */
    private Date outDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(String totalCode) {
        this.totalCode = totalCode;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
