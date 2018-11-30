package com.baojie.manage.back.baojie.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

import com.baojie.manage.back.baojie.dao.BStaffDao;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("com.baojie.manage.back.baojie.dao.impl.BStaffDaoImpl")
public class BStaffDaoImpl extends AbstractHibernateEntityDao<StaffEntity> implements BStaffDao {

	@Override
	public StaffEntity getStaffByName(String name) throws BizException {
		String hql = "from StaffEntity e where  e.name=:name";
		Map<String, Object> params = Maps.newHashMap();
		params.put("name", name);
		List<StaffEntity> entitieList = selectList(hql, params);
		StaffEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}
	/**
	 * 	CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StaffEntity> createQuery = builder.createQuery(StaffEntity.class);
		Root<StaffEntity> root = createQuery.from(StaffEntity.class);
		createQuery.select(root);
		createQuery.where(builder.like(root.get("towerName"), "%"+towerName+"%"));
		List<StaffEntity> list2 = session.createQuery(createQuery).getResultList();
	 */
	@Override
	public PageResults<StaffEntity> getStaffList(Integer pageNo, Integer pageSize, String towerName,
			String positionName) throws BizException {
		List<StaffEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<StaffEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<StaffEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(StaffEntity.class);
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", "%"+towerName+"%"));
				}
				if (StringUtils.isNotEmpty(positionName)) {
					criteria.add(Restrictions.like("positionName", "%"+positionName+"%"));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryStaffListCount(pageNo, pageSize, towerName, positionName);
		PageResults<StaffEntity> results = new PageResults<StaffEntity>(list, pageNo, pageSize, count);
		return results;
	}

	@Override
	public StaffEntity addorUpdateStaff(StaffEntity StaffEntity) throws BizException {
		return save(StaffEntity);
	}

	@Override
	public void deleteStaff(StaffEntity Staff) throws BizException {
		delete(Staff);

	}

	@Override
	public List<StaffEntity> getStaffListByStaffIds(List<Long> ids) throws BizException {
		if (CollectionUtils.isNotEmpty(ids)) {
			List<StaffEntity> entitieList = selectEntitiesByPKs(ids);
			if (CollectionUtils.isEmpty(entitieList)) {
				return Lists.newArrayList();
			}
			return entitieList;
		}
		return null;
	}

	public Long queryStaffListCount(Integer pageNo, Integer pageSize, String towerName, String positionName)
			throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(StaffEntity.class);
				if (StringUtils.isNotEmpty(towerName)) {
					criteria.add(Restrictions.like("towerName", "%"+towerName+"%"));
				}
				if (StringUtils.isNotEmpty(positionName)) {
					criteria.add(Restrictions.like("positionName", "%"+positionName+"%"));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}
}
