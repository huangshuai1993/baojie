package com.baojie.manage.back.baojie.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.Entity.PositionEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("com.baojie.manage.back.baojie.dao.impl.PositionDaoImpl")
public class PositionDaoImpl extends AbstractHibernateEntityDao<PositionEntity> implements PositionDao {

	@Override
	public PositionEntity getPositionByName(String name) throws BizException {
		String hql = "from PositionEntity e where  e.towerName=:towerName";
		Map<String, Object> params = Maps.newHashMap();
		params.put("towerName", name);
		List<PositionEntity> entitieList = selectList(hql, params);
		PositionEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	@Override
	public PageResults<PositionEntity> getPositionList(Integer pageNo, Integer pageSize, String towerName)
			throws BizException {
		List<PositionEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<PositionEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<PositionEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(PositionEntity.class);
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", towerName));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryPositionListCount(pageNo, pageSize, towerName);
		PageResults<PositionEntity> results = new PageResults<PositionEntity>(list, pageNo, pageSize, count);
		return results;
	}

	@Override
	public PositionEntity addorUpdatePosition(PositionEntity positionEntity) throws BizException {
		return save(positionEntity);
	}

	@Override
	public void deletePosition(PositionEntity Position) throws BizException {
		delete(Position);
	}

	@Override
	public List<PositionEntity> getPositionListByPositionIds(List<Long> ids) throws BizException {
		if (CollectionUtils.isNotEmpty(ids)) {
			List<PositionEntity> entitieList = selectEntitiesByPKs(ids);
			if (CollectionUtils.isEmpty(entitieList)) {
				return Lists.newArrayList();
			}
			return entitieList;
		}
		return null;
	}

	public Long queryPositionListCount(Integer pageNo, Integer pageSize, String towerName) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(PositionEntity.class);
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", towerName));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}

}
