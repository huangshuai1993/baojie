package com.baojie.manage.back.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.back.sys.mapper.Persona_powerEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.exception.BizException;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class Persona_powerDao extends BaseDao<Persona_powerEntity> {

	@Autowired
	private Persona_powerEntityMapper persona_powerEntityMapper;

	public List<Persona_powerEntity> getEntityByPersonaId(Long personaId) throws BizException {
		Example example = new Example(Persona_powerEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("personaId",personaId);
		return persona_powerEntityMapper.selectByExample(example);
	}
}
