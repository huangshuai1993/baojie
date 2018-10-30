package com.baojie.manage.back.sys.dao;

import java.util.List;

import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface EmployeeDao extends IEntityDao<EmployeeEntity> {
	public EmployeeEntity getEmployeeByUserName(String userName) throws BizException;

	public PageResults<EmployeeEntity> getEmployeeList(Integer pageNo, Integer pageSize) throws BizException;

	public EmployeeEntity addorUpdateEmployee(EmployeeEntity employee) throws BizException;

	public EmployeeEntity getEmployeeByCustNo(String custNo) throws BizException;

	public void deleteEmployee(EmployeeEntity employee) throws BizException;

	public List<EmployeeEntity> getEmployeeListByEmployeeIds(List<Long> ids) throws BizException;
}
