package com.baojie.manage.back.baojie.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("com.baojie.manage.back.baojie.dao.impl.SalaryDaoImpl")
public class SalaryDaoImpl extends AbstractHibernateEntityDao<SalaryEntity> implements SalaryDao {
	
}
