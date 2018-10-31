package com.baojie.manage.back.baojie.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.Entity.TowerEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("com.baojie.manage.back.baojie.dao.impl.BTowerDaoImpl")
public class BTowerDaoImpl extends AbstractHibernateEntityDao<TowerEntity> implements BTowerDao {

}
