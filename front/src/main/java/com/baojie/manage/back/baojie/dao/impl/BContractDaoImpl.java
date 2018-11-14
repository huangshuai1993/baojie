package com.baojie.manage.back.baojie.dao.impl;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dao.Entity.ContractEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;

@Repository("com.baojie.manage.back.baojie.dao.impl.BContractDaoImpl")
public class BContractDaoImpl extends AbstractHibernateEntityDao<ContractEntity> implements BContractDao {

}
