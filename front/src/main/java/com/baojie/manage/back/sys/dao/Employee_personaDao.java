package com.baojie.manage.back.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.mapper.Employee_personaEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.exception.BizException;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Service
public class Employee_personaDao extends BaseDao<Employee_personaEntity> {

	@Autowired
	private Employee_personaEntityMapper employee_personaEntityMapper;

	public Employee_personaEntity getEmployee_personaByEmployeeId(Long employeeId) {
		Example example = new Example(Employee_personaEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("employeeId", employeeId);
		List<Employee_personaEntity> list = employee_personaEntityMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	public List<Employee_personaEntity> getEmployeeIdBypersonaId(Long personaId) throws BizException {
		Example example = new Example(Employee_personaEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("personaId", personaId);
		return employee_personaEntityMapper.selectByExample(example);
	}
}
