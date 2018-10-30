package com.baojie.manage.back.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.PersonaDao;
import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("personaDaoImpl")
public class PersonaDaoImpl extends AbstractHibernateEntityDao<PersonaEntity> implements PersonaDao {

	@Override
	public PersonaEntity findPersonaBypersonaId(Long personaId) throws BizException {
		String hql = "from PersonaEntity p where  p.personaId=:personaId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("personaId", personaId);
		List<PersonaEntity> entitieList = selectList(hql, params);
		PersonaEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	@Override
	public List<PersonaEntity> getAllPersonas() throws BizException {
		String hql = "from PersonaEntity p";
		List<PersonaEntity> entitieList = selectList(hql, null);
		return entitieList;
	}

	@Override
	public List<PersonaEntity> findPersonaBypersonaName(String name) throws BizException {
		String hql = "from PersonaEntity p where  p.personaName=:personaName";
		Map<String, Object> params = Maps.newHashMap();
		params.put("personaName", name);
		List<PersonaEntity> entitieList = selectList(hql, params);
		if (CollectionUtils.isEmpty(entitieList)) {
			return Lists.newArrayList();
		}
		return entitieList;
	}

}
