package com.baojie.manage.back.baojie.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.BStaffDao;
import com.baojie.manage.back.baojie.dao.Entity.StaffEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("com.baojie.manage.back.baojie.dao.impl.BStaffDaoImpl")
public class BStaffDaoImpl extends AbstractHibernateEntityDao<StaffEntity> implements BStaffDao {

}
