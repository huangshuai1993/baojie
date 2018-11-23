package com.baojie.manage.back.baojie.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.Entity.PositionEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("com.baojie.manage.back.baojie.dao.impl.PositionDaoImpl")
public class PositionDaoImpl extends AbstractHibernateEntityDao<PositionEntity> implements PositionDao {

}
