package com.baojie.manage.back.sys.dao;

import java.util.List;

import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface Persona_powerDao extends IEntityDao<Persona_powerEntity> {

	public List<Persona_powerEntity> getEntityByPersonaId(Long personaId) throws BizException;
}
