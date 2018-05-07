package com.microwise.halley.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseDaoImpl
 *
 * @author li.jianfei
 * @date 2013-11-04
 */
public class BaseDaoImpl extends SqlSessionDaoSupport {

    @Autowired
    public void initSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
