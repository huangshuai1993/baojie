package com.baojie.manage.back.baojie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("salaryService")
public class SalaryServiceImpl extends BaseService implements SalaryService {

	@Autowired
	private SalaryDao salaryDao;

	@Override
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, String towerName,
			String positionName) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.getAllSalary------------begin-->");
		}
		PageResults<SalaryForm> response = new PageResults<SalaryForm>();
		try {
		} catch (Exception e) {
			logger.error("SalaryServiceImpl.getAllSalary发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.getAllSalary------------end-->");
			}
		}
		return response;
	}

}
