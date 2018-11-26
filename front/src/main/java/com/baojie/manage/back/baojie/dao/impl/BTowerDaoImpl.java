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

import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.Entity.TowerEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("com.baojie.manage.back.baojie.dao.impl.BTowerDaoImpl")
public class BTowerDaoImpl extends AbstractHibernateEntityDao<TowerEntity> implements BTowerDao {

	@Override
	public TowerEntity getTowerByName(String name) throws BizException {
		String hql = "from TowerEntity e where  e.towerName=:towerName";
		Map<String, Object> params = Maps.newHashMap();
		params.put("towerName", name);
		List<TowerEntity> entitieList = selectList(hql, params);
		TowerEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	public PageResults<TowerEntity> getTowerList(Integer pageNo, Integer pageSize) throws BizException {
		String hql = "from TowerEntity e order by e.towerId desc";
		PageResults<TowerEntity> result = listPage(hql, null, pageNo, pageSize);
		return result;
	}

	@Override
	public TowerEntity addorUpdateTower(TowerEntity towerEntity) throws BizException {
		TowerEntity entity = save(towerEntity);
		return entity;
	}

	@Override
	public void deleteTower(TowerEntity tower) throws BizException {
		delete(tower);
	}

	@Override
	public List<TowerEntity> getTowerListByTowerIds(List<Long> ids) throws BizException {
		if (CollectionUtils.isNotEmpty(ids)) {
			List<TowerEntity> entitieList = selectEntitiesByPKs(ids);
			if (CollectionUtils.isEmpty(entitieList)) {
				return Lists.newArrayList();
			}
			return entitieList;
		}
		return null;
	}

	@Override
	public PageResults<TowerEntity> getTowerList(Integer pageNo, Integer pageSize, String towerName,
			String functionaryName) throws BizException {
		List<TowerEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<TowerEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<TowerEntity> doInHibernate(Session session) throws HibernateException {

				Criteria criteria = session.createCriteria(TowerEntity.class);
				if (StringUtils.isNotEmpty(functionaryName)) {
					criteria.add(Restrictions.like("functionaryName", functionaryName));
				}
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", towerName));
				}

				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryTowerListCount(pageNo, pageSize, towerName, functionaryName);
		PageResults<TowerEntity> result = new PageResults<TowerEntity>(list, pageNo, pageSize, count);
		return result;
	}

	public Long queryTowerListCount(Integer pageNo, Integer pageSize, String towerName, String functionaryName)
			throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TowerEntity.class);
				if (StringUtils.isNotEmpty(functionaryName)) {
					criteria.add(Restrictions.like("functionaryName", functionaryName));
				}
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
