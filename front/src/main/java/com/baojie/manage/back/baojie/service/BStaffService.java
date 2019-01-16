package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BStaffService {
	
	public PageResults<StaffForm> getAllStaff(Integer pageNumber, Integer pageSize, Long towerId,String staffName,Integer status,Integer startAge,Integer endAge,Integer gender)
			throws BizException;

	public Integer addStaff(StaffForm staffForm) throws BizException;

	public Map<String, Object> deleteStaff(Long id) throws BizException;
	public Map<String, Object> getStaffInfo(Long id) throws BizException;
	
	public Map<String, Object> getPositionListByTowerId(Long id) throws BizException;
	public Map<String, String> getStaffStatusType();
}
