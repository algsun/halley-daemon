package com.microwise.halley.dao;

import com.microwise.halley.bean.vo.UserVO;

import java.util.List;

/**
 * 用户 Dao
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
public interface UserDao {

    /**
     * 根据查询外展相关人员信息
     *
     * @param exhibitionId 外展ID
     * @return 人员列表
     */
    public List<UserVO> findUsersByExhibitionId(int exhibitionId);
}
