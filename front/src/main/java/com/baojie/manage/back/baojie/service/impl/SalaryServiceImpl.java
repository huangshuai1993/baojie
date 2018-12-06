package com.baojie.manage.back.baojie.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("salaryService")
public class SalaryServiceImpl extends BaseService implements SalaryService {

	@Autowired
	private SalaryDao salaryDao;

	@Override
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.getAllSalary------------begin-->");
		}
		PageResults<SalaryForm> page = new PageResults<SalaryForm>();
		try {
			PageResults<SalaryEntity> allSalary = salaryDao.getAllSalary(pageNumber, pageSize, towerId, searchName);
			if (allSalary != null) {
				List<SalaryEntity> list = allSalary.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<SalaryForm> list2 = BeanUtils.copyByList(list, SalaryForm.class);
					page = new PageResults<SalaryForm>(list2, pageNumber, pageSize, allSalary.getTotalCount());
				}
			}
		} catch (Exception e) {
			logger.error("SalaryServiceImpl.getAllSalary发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.getAllSalary------------end-->");
			}
		}
		return page;
	}

}
