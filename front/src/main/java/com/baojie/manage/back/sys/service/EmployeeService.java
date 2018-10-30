package com.baojie.manage.back.sys.service;

import java.util.Map;

import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface EmployeeService {

	public PageResults<EmployeeDto> getEmployeePageList(String personaName, String parameName, Integer pageNumber,
			Integer pageSize) throws BizException;

	public EmployeeDto getEmployeeByUserName(String userName) throws BizException;

	public Long addEmployee(EmployeeDto employee, Long personaId) throws BizException;

	public Map<String, Object> updateEnable(String empStatus, String custNo) throws BizException;

	/**
	 * 更改员工密码
	 * 
	 * @param custNo
	 * @return
	 */
	public Map<String, Object> updatePassword(String password, String custNo) throws BizException;

	/**
	 * 得到员工详细信息
	 * 
	 * @param custNo
	 * @return
	 * @throws BizException
	 */
	public Map<String, Object> getEmployeeInfo(String custNo) throws BizException;

	/**
	 * 删除员工
	 * 
	 * @param custNo
	 * @return
	 */
	public Map<String, Object> deleteEmployee(String custNo);

	/**
	 * 更新员工信息
	 * 
	 * @param custNo
	 * @return
	 */
	public Map<String, Object> updateEmployee(EmployeeDto employee, Long personaId);

}
