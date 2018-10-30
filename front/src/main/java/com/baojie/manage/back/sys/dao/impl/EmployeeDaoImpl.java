package com.baojie.manage.back.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.EmployeeDao;
import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("employeeDaoImpl")
public class EmployeeDaoImpl extends AbstractHibernateEntityDao<EmployeeEntity> implements EmployeeDao {

	public EmployeeEntity getEmployeeByUserName(String userName) throws BizException {
		EmployeeDto dto = null;
		String hql = "from EmployeeEntity e where  e.username=:userName";
		Map<String, Object> params = Maps.newHashMap();
		params.put("userName", userName);

		List<EmployeeEntity> entitieList = selectList(hql, params);
		EmployeeEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	@Override
	public PageResults<EmployeeEntity> getEmployeeList(Integer pageNo, Integer pageSize) throws BizException {
		String hql = "from EmployeeEntity e order by e.id desc";
		PageResults<EmployeeEntity> result = listPage(hql, null, pageNo, pageSize);
		return result;
	}

	@Override
	public EmployeeEntity addorUpdateEmployee(EmployeeEntity employee) throws BizException {
		EmployeeEntity entity = save(employee);
		return entity;
	}

	@Override
	public EmployeeEntity getEmployeeByCustNo(String custNo) throws BizException {
		String hql = "from EmployeeEntity e where  e.custNo=:custNo";
		Map<String, Object> params = Maps.newHashMap();
		params.put("custNo", custNo);
		List<EmployeeEntity> entitieList = selectList(hql, params);
		EmployeeEntity emp = null;
		if (entitieList != null && !entitieList.isEmpty()) {
			emp = entitieList.get(0);
		}
		return emp;
	}

	@Override
	public void deleteEmployee(EmployeeEntity employee) throws BizException {
		delete(employee);
	}

	@Override
	public List<EmployeeEntity> getEmployeeListByEmployeeIds(List<Long> ids) throws BizException {
		if (CollectionUtils.isNotEmpty(ids)) {
			List<EmployeeEntity> entitieList = selectEntitiesByPKs(ids);
			if (CollectionUtils.isEmpty(entitieList)) {
				return Lists.newArrayList();
			}
			return entitieList;
		}
		return null;
	}

}
