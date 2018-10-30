package com.baojie.manage.back.login.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.login.dao.LoginLogDao;
import com.baojie.manage.back.login.dao.entity.LoginLogEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("loginLogDaoImpl")
public class LoginLogDaoImpl extends AbstractHibernateEntityDao<LoginLogEntity> implements LoginLogDao{

}
