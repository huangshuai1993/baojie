package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface SalaryService {
	
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException;
	
	public Integer addSalaryMonth() throws Exception;
	
	public Map<String, Object> getSalaryInfo(Long id)throws BizException;
	
	public Map<String, Object> updateStaffSalary(SalaryForm salary)throws BizException;
	
	public Map<String, Object> deleteSalary(Long id) throws BizException;
}
