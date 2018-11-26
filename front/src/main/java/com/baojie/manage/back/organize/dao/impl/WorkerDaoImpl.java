package com.baojie.manage.back.organize.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.organize.dao.WorkerDao;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.dao.entity.WorkerEntity;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

@Repository("workerDaoImpl")
public class WorkerDaoImpl extends AbstractHibernateEntityDao<WorkerEntity> implements WorkerDao {

	@Override
	public PageResults<WorkerEntity> getWorkerList(final Long storeId, final String name, final String mobile,
			final Integer status, final Integer pageNumber, final Integer pageSize) throws BizException {
		List<WorkerEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<WorkerEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<WorkerEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(WorkerEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (status != 10) {
					criteria.add(Restrictions.eq("status", status));
				}
				if (storeId != null) {
					criteria.add(Restrictions.eq("storeId", storeId));
				}
				criteria.addOrder(Order.desc("created"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryListCount(storeId, name, mobile, status, pageNumber, pageSize);
		PageResults<WorkerEntity> result = new PageResults<WorkerEntity>(list, pageNumber, pageSize, count);
		return result;
	}

	public Long queryListCount(final Long storeId, final String name, final String mobile, final Integer status,
			final Integer pageNumber, final Integer pageSize) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(WorkerEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (status != 10) {
					criteria.add(Restrictions.eq("status", status));
				}
				if (storeId != null) {
					criteria.add(Restrictions.eq("storeId", storeId));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});

		return count == null ? 0 : count;
	}

	@Override
	public WorkerEntity getWorkerByIdCard(String idcardNo) throws BizException {
		String hql = "from WorkerEntity w where  w.idcardNo=:idcardNo";
		Map<String, Object> params = Maps.newHashMap();
		params.put("idcardNo", idcardNo);
		List<WorkerEntity> entitieList = selectList(hql, params);
		WorkerEntity worker = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			worker = entitieList.get(0);
		}
		return worker;
	}

	@Override
	public WorkerEntity getWorkerByMobile(String mobile) throws BizException {
		String hql = "from WorkerEntity w where  w.mobile=:mobile";
		Map<String, Object> params = Maps.newHashMap();
		params.put("mobile", mobile);
		List<WorkerEntity> entitieList = selectList(hql, params);
		WorkerEntity worker = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			worker = entitieList.get(0);
		}
		return worker;
	}

	@Override
	public List<WorkerEntity> getWorkerByStoreId(Long storeId) throws BizException {
		String hql = "from WorkerEntity w where  w.storeId=:storeId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("storeId", storeId);
		List<WorkerEntity> entitieList = selectList(hql, params);
		return entitieList;
	}
}