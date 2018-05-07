package com.microwise.halley.dao;

import com.microwise.halley.bean.vo.ExhibitionVO;
import com.microwise.halley.bean.vo.RelicVO;

import java.util.List;

/**
 * 外展 Dao
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
public interface ExhibitionDao {

    /**
     * 送出库记录中同步外展记录
     *
     * @param siteId 站点ID
     */
    public void syncExhibitions(String siteId);

    /**
     * 查询当前站点下所有外展记录
     *
     * @param siteId 站点ID
     * @return 外展记录集合
     */
    public List<ExhibitionVO> findExhibitionList(String siteId, int state);

    /**
     * 查询当前站点下所有外展记录
     *
     * @return 外展集合
     */
    public List<ExhibitionVO> findExhibitionListNotEnd();

    /**
     * 查询外展文物列表
     *
     * @param exhibitionId 外展ID
     * @return 文物列表
     */
    public List<RelicVO> findExhibitionRelicList(int exhibitionId);
}