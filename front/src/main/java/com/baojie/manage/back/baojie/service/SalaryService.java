package com.baojie.manage.back.baojie.service;

import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface SalaryService {
	
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException;
	
	public Integer addSalaryMonth() throws BizException;
}
