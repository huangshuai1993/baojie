package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.back.baojie.mapper.SalaryEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class SalaryDao extends BaseDao<SalaryEntity>{

	@Autowired
	private SalaryEntityMapper salaryEntityMapper;

	public List<SalaryEntity> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId, String staffName,
			String time) {
		PageHelper.startPage(pageNumber, pageSize);
		Example example = new Example(SalaryEntity.class);
		Example.Criteria c = example.createCriteria();
		if (towerId != null) {
			c.andEqualTo("towerId", towerId);
		}
		if (StringUtils.isNotBlank(staffName)) {
			c.andLike("staffName", staffName);
		}
		if (StringUtils.isNotBlank(time)) {
			c.andLike("salaryMonth", time);
		}
		example.orderBy("updated").desc();
		return salaryEntityMapper.selectByExample(example);
	}

	public int queryCountSalaryByMonth(String time) {
		Example example = new Example(SalaryEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andLike("salaryMonth", time);
		return salaryEntityMapper.selectCountByExample(example);
	}

}
