package com.baojie.manage.back.baojie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.base.service.BaseService;

@Service("salaryService")
public class SalaryServiceImpl extends BaseService implements SalaryService {

	@Autowired
	private SalaryDao salaryDao;

}
