package com.baojie.manage.back.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.PowerDao;
import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("powerDaoImpl")
public class PowerDaoImpl extends AbstractHibernateEntityDao<PowerEntity> implements PowerDao {

	public List<PowerEntity> getPowerLevelTwo() throws BizException {
		List<PowerEntity> en = Lists.newArrayList();
		String hql = "from PowerEntity p where p.parentId is not null";
		en = selectList(hql);
		return en;
	}

	public List<PowerEntity> getLevelOne() throws BizException {
		List<PowerEntity> en = Lists.newArrayList();
		String hql = "from PowerEntity p where p.parentId is null";
		en = selectList(hql);
		return en;
	}

	@Override
	public List<PowerEntity> getPowerByPowerId(List<Long> powerId) throws BizException {
		List<PowerEntity> entitieList = selectEntitiesByPKs(powerId);
		return entitieList;
	}

	@Override
	public PowerEntity getPowerByParentId(Long parentId) throws BizException {
		String hql = "from PowerEntity p where  p.parentId=:parentId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("parentId", parentId);
		List<PowerEntity> entitieList = selectList(hql, params);
		PowerEntity pow = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			pow = entitieList.get(0);
		}
		return pow;
	}

	@Override
	public List<PowerEntity> getPowerDatas() throws BizException {
		List<PowerEntity> list = Lists.newArrayList();
		String hql = "from PowerEntity p where p.parentId is not null";
		list = selectList(hql);
		return list;
	}

}
