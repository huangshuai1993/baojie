package com.baojie.manage.back.baojie.service;

import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface SalaryService {
	
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, String towerName,
			String positionName) throws BizException;
}
