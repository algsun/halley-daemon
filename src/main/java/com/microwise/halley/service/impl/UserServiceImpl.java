package com.microwise.halley.service.impl;

import com.microwise.halley.bean.vo.UserVO;
import com.microwise.halley.dao.UserDao;
import com.microwise.halley.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户 Service 实现
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
@Service
@Scope("prototype")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVO> findUsersByExhibitionId(int exhibitionId) {
        return userDao.findUsersByExhibitionId(exhibitionId);
    }
}
