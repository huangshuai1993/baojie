package com.baojie.manage.back.sys.dao;

import java.util.List;

import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface Employee_personaDao  extends IEntityDao<Employee_personaEntity> {
	 public Employee_personaEntity getEmployee_personaByEmployeeId(Long employeeId) throws BizException;
	 
	 public Employee_personaEntity addOrUpdataEmployeePersona(Employee_personaEntity dto)throws BizException;
	 
	 public void deleteEmployee_persona(Long empId)throws BizException;
	 
	 public List<Employee_personaEntity> getEmployeeIdBypersonaId(Long personaId) throws BizException;
	 
	 public List<Employee_personaEntity> getAllEmployee_persona() throws BizException;
}
