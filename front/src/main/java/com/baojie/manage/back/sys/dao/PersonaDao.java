package com.baojie.manage.back.sys.dao;

import java.util.List;

import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface PersonaDao extends IEntityDao<PersonaEntity> {
	public PersonaEntity findPersonaBypersonaId(Long personaId) throws BizException;

	public List<PersonaEntity> getAllPersonas() throws BizException;

	public List<PersonaEntity> findPersonaBypersonaName(String name) throws BizException;
}
