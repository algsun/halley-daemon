package com.microwise.halley.bean.vo;

/**
 * 文物装箱单明细
 *
 * @author li.jianfei
 * @date 2013-11-05
 */
public class PackingRelicVO {

    /**
     * id
     */
    private int id;

    /**
     * 集装箱ID
     */
    private int packingId;

    /**
     * 文物总登记号
     */
    private int relicId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackingId() {
        return packingId;
    }

    public void setPackingId(int packingId) {
        this.packingId = packingId;
    }

    public int getRelicId() {
        return relicId;
    }

    public void setRelicId(int relicId) {
        this.relicId = relicId;
    }
}
