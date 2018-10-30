package com.baojie.manage.back.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.Persona_powerDao;
import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("persona_powerDaoImpl")
public class Persona_powerDaoImpl extends AbstractHibernateEntityDao<Persona_powerEntity> implements Persona_powerDao {

	public List<Persona_powerEntity> getEntityByPersonaId(Long personaId) throws BizException {
		List<Persona_powerEntity> list = Lists.newArrayList();
		String hql = "from Persona_powerEntity p where p.personaId=:personaId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("personaId", personaId);
		list = selectList(hql, params);
		return list;
	}

}
