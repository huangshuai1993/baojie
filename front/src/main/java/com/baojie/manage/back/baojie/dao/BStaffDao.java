package com.baojie.manage.back.baojie.dao;

import java.util.List;

import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface BStaffDao extends IEntityDao<StaffEntity> {
	public List<StaffEntity> getStaffByTowerId(Long towerId) throws BizException;

	public PageResults<StaffEntity> getStaffList(Integer pageNo, Integer pageSize,Long towerId,String staffName) throws BizException;

	public StaffEntity addorUpdateStaff(StaffEntity StaffEntity) throws BizException;

	public void deleteStaff(StaffEntity Staff) throws BizException;

	public List<StaffEntity> getStaffListByStaffIds(List<Long> ids) throws BizException;
}
