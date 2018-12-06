package com.baojie.manage.back.baojie.dao;

import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface SalaryDao extends IEntityDao<SalaryEntity> {
	
	public PageResults<SalaryEntity> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException;

	public long queryCountSalaryByMonth(String time)throws BizException;
}
