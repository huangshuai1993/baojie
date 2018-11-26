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

import com.baojie.manage.back.organize.dao.StoreDao;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.dao.entity.StoreEntity;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

@Repository("storeDaoImpl")
public class StoreDaoImpl extends AbstractHibernateEntityDao<StoreEntity> implements StoreDao {

	@Override
	public PageResults<StoreEntity> getStoreList(final String province, final String name, final Integer pageNumber,
			final Integer pageSize) throws BizException {
		List<StoreEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<StoreEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<StoreEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(StoreEntity.class);
				if (StringUtils.isNotEmpty(province)) {
					criteria.add(Restrictions.eq("provinceCode", province));
				}
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.like("name", "%" + name + "%"));
				}
				criteria.addOrder(Order.desc("created"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryListCount(province, name, pageNumber, pageSize);
		PageResults<StoreEntity> result = new PageResults<StoreEntity>(list, pageNumber, pageSize, count);
		return result;
	}

	public Long queryListCount(final String province, final String name, final Integer pageNumber,
			final Integer pageSize) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(StoreEntity.class);
				if (StringUtils.isNotEmpty(province)) {
					criteria.add(Restrictions.eq("provinceCode", province));
				}
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.like("name", "%" + name + "%"));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count;
	}

	@Override
	public StoreEntity getStoreByName(String name) throws BizException {
		String hql = "from StoreEntity s where  s.name=:name";
		Map<String, Object> params = Maps.newHashMap();
		params.put("name", name);
		List<StoreEntity> entitieList = selectList(hql, params);
		StoreEntity store = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			store = entitieList.get(0);
		}
		return store;
	}

}
