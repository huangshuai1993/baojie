package com.baojie.manage.back.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.mapper.EmployeeEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class EmployeeDao extends BaseDao<EmployeeEntity> {

	@Autowired
	private EmployeeEntityMapper employeeEntityMapper;
	
	public List<EmployeeEntity> getEmployeeList(Integer pageNo, Integer pageSize){
		PageHelper.startPage(pageNo, pageSize);
		Example example = new Example(EmployeeEntity.class);
	    example.orderBy("updated").desc();
		return employeeEntityMapper.selectByExample(example);
	}
	
	public EmployeeEntity getEmployeeByUserName(String userName){
		Example example = new Example(EmployeeEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("userName",userName);
		List<EmployeeEntity> list = employeeEntityMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	public EmployeeEntity getEmployeeByCustNo(String custNo){
		Example example = new Example(EmployeeEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("custNo",custNo);
		List<EmployeeEntity> list = employeeEntityMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	public List<EmployeeEntity> getEmployeeListByEmployeeIds(List<Long> ids) throws BizException {
		if (!CollectionUtils.isEmpty(ids)) {
			Example example = new Example(EmployeeEntity.class);
			Example.Criteria c = example.createCriteria();
			c.andIn("employeeId", ids);
			List<EmployeeEntity> list = employeeEntityMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				return Lists.newArrayList();
			}
			return list;
		}
		return null;
	}

}
