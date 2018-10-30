package com.baojie.manage.back.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.back.sys.dao.Employee_personaDao;
import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

@Repository("employee_personaDaoImpl")
public class Employee_personaDaoImpl extends AbstractHibernateEntityDao<Employee_personaEntity>
		implements Employee_personaDao {

	@Override
	public Employee_personaEntity getEmployee_personaByEmployeeId(Long employeeId) throws BizException {
		String hql = "from Employee_personaEntity e where  e.employeeId=:employeeId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("employeeId", employeeId);
		List<Employee_personaEntity> entitieList = selectList(hql, params);
		Employee_personaEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	@Override
	public Employee_personaEntity addOrUpdataEmployeePersona(Employee_personaEntity entity) throws BizException {
		if (entity.getEmployeeId() == null || entity.getPersonaId() == null) {
			throw new BizException(ExampleExCode.ID_IS_NULL);
		}
		Employee_personaEntity employee_personaEntity = save(entity);
		return employee_personaEntity;
	}

	@Override
	public void deleteEmployee_persona(Long empId) throws BizException {
		if (empId == null) {
			throw new BizException(ExampleExCode.ID_IS_NULL);
		}
		deleteByPK(empId);
	}

	@Override
	public List<Employee_personaEntity> getEmployeeIdBypersonaId(Long personaId) throws BizException {
		String hql = "from Employee_personaEntity p where  p.personaId=:personaId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("personaId", personaId);
		List<Employee_personaEntity> entitieList = selectList(hql, params);
		return entitieList;
	}

	@Override
	public List<Employee_personaEntity> getAllEmployee_persona() throws BizException {
		String hql = "from Employee_personaEntity p";
		List<Employee_personaEntity> entitieList = selectList(hql, null);
		return entitieList;
	}

}
