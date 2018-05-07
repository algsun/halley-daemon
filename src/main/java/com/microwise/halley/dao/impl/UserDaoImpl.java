package com.microwise.halley.dao.impl;

import com.microwise.halley.bean.vo.UserVO;
import com.microwise.halley.dao.UserDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户 Dao 实现
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
@Component
@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public List<UserVO> findUsersByExhibitionId(int exhibitionId) {
        return getSqlSession().selectList("UserDao.findUsersByExhibitionId", exhibitionId);
    }
}
