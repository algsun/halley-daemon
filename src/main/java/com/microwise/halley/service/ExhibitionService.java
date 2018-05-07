package com.microwise.halley.service;

import com.microwise.halley.bean.vo.CarVO;
import com.microwise.halley.bean.vo.ExhibitionVO;

import java.util.List;

/**
 * 外展记录 Service
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
public interface ExhibitionService {

    /**
     * 获取指定站点下所有准备中的外展记录（包含车辆、外展文物信息）
     *
     * @param siteId 站点ID
     * @return 外展记录
     */
    public List<ExhibitionVO> findExhibitionList(String siteId);

    /**
     * 获取所有站点下当前状态为运输中的外展
     *
     * @return 外展集合
     */
    public List<ExhibitionVO> findExhibitionListNotEnd();

    /**
     * 上传文物外展装箱单
     *
     * @param exhibitionId 外展ID
     */
    public void uploadExhibitionPackingList(int exhibitionId, List<CarVO> carList);
}
