package com.baojie.manage.back.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.back.sys.mapper.PersonaEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.exception.BizException;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class PersonaDao extends BaseDao<PersonaEntity> {

	@Autowired
	private PersonaEntityMapper personaEntityMapper;

	public PersonaEntity findPersonaBypersonaId(Long personaId) {
		Example example = new Example(PersonaEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("personaId", personaId);
		List<PersonaEntity> list = personaEntityMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public List<PersonaEntity> findPersonaBypersonaName(String name) throws BizException {
		Example example = new Example(PersonaEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("personaName", name);
		return personaEntityMapper.selectByExample(example);
	}
}
