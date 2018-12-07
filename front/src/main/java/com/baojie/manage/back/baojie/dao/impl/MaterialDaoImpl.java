package com.baojie.manage.back.baojie.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.MaterialDao;
import com.baojie.manage.back.baojie.dao.entity.MaterialEntity;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("com.baojie.manage.back.baojie.dao.impl.MaterialDaoImpl")
public class MaterialDaoImpl extends AbstractHibernateEntityDao<MaterialEntity> implements MaterialDao {

	public Long queryMaterialListCount(Integer pageNo, Integer pageSize, Long towerId) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(PositionEntity.class);
				if (towerId != null) {
					criteria.add(Restrictions.eq("towerId", towerId));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}

	@Override
	public PageResults<MaterialEntity> getMaterialList(Integer pageNo, Integer pageSize, Long towerId)
			throws BizException {
		List<MaterialEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<MaterialEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<MaterialEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(MaterialEntity.class);
				if (towerId != null) {
					criteria.add(Restrictions.eq("towerId", towerId));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryMaterialListCount(pageNo, pageSize, towerId);
		PageResults<MaterialEntity> results = new PageResults<MaterialEntity>(list, pageNo, pageSize, count);
		return results;
	}

	@Override
	public MaterialEntity addorUpdateMaterial(MaterialEntity materialEntity) throws BizException {
		return save(materialEntity);
	}

	@Override
	public void deleteMaterial(MaterialEntity materialEntity) throws BizException {
		delete(materialEntity);
	}

	@Override
	public List<MaterialEntity> getMaterialListByMaterialIds(List<Long> ids) throws BizException {
		if (CollectionUtils.isNotEmpty(ids)) {
			List<MaterialEntity> entitieList = selectEntitiesByPKs(ids);
			if (CollectionUtils.isEmpty(entitieList)) {
				return Lists.newArrayList();
			}
			return entitieList;
		}
		return null;
	}

	@Override
	public List<MaterialEntity> getMaterialListByTowerId(Long id) throws BizException {
		String hql = "from MaterialEntity e where  e.towerId=:towerId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("towerId", id);
		return selectList(hql, params);
	}

}
