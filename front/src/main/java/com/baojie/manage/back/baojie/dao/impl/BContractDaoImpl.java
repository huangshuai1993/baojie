package com.baojie.manage.back.baojie.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dao.Entity.ContractEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;

@Repository("com.baojie.manage.back.baojie.dao.impl.BContractDaoImpl")
public class BContractDaoImpl extends AbstractHibernateEntityDao<ContractEntity> implements BContractDao {

	@SuppressWarnings("deprecation")
	@Override
	public PageResults<ContractEntity> getContractList(final Integer pageNumber, final Integer pageSize,
			final String contractName, final String towerName, final Integer status) throws BizException {
		List<ContractEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<ContractEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ContractEntity> doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(ContractEntity.class);
				if (StringUtils.isNotEmpty(contractName)) {
					criteria.add(Restrictions.like("contractName", contractName));
				}
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", towerName));
				}
				if (status != null) {
					criteria.add(Restrictions.eq("status", status));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryContractListCount(pageNumber, pageSize, contractName, towerName, status);
		PageResults<ContractEntity> result = new PageResults<ContractEntity>(list, pageNumber, pageSize, count);
		return result;
	}

	@SuppressWarnings("deprecation")
	public Long queryContractListCount(final Integer pageNumber, final Integer pageSize, final String contractName,
			final String towerName, final Integer status) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(ContractEntity.class);
				if (StringUtils.isNotEmpty(contractName)) {
					criteria.add(Restrictions.like("contractName", contractName));
				}
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", towerName));
				}
				if (status != null) {
					criteria.add(Restrictions.eq("status", status));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}
}
