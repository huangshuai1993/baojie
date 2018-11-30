package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BStaffDao;
import com.baojie.manage.back.baojie.dao.entity.ContractEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.back.baojie.service.BStaffService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("bstaffService")
public class BStaffServiceImpl extends BaseService implements BStaffService {
	@Autowired
	private BStaffDao staffDao;

	@Override
	public PageResults<StaffForm> getAllStaff(Integer pageNumber, Integer pageSize, String towerName,
			String positionName) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.getAllStaff------------begin-->");
		}
		PageResults<StaffForm> response = new PageResults<StaffForm>();
		try {
			PageResults<StaffEntity> staffList = staffDao.getStaffList(pageNumber, pageSize, towerName, positionName);
			if (staffList != null) {
				List<StaffEntity> list = staffList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<StaffForm> list2 = BeanUtils.copyByList(list, StaffForm.class);
					response = new PageResults<StaffForm>(list2, pageNumber, pageSize, staffList.getTotalCount());
				}
			}
		} catch (Exception e) {
			logger.error("BStaffServiceImpl.getAllStaff发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BStaffServiceImpl.getAllStaff------------end-->");
			}
		}
		return response;
	}

	@Override
	public Integer addStaff(StaffForm staffForm) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.addStaff------------begin-->");
		}
		Integer result = 0;
		try {
			if (staffForm == null) {
				return result;
			}
			StaffEntity entity = null;
			if(staffForm.getId() != null){
				entity = staffDao.selectByPK(staffForm.getId());
				BeanUtils.copyPropertiesNotNUll(staffForm, entity);
				entity.setUpdated(new Date());
				entity = staffDao.update(entity);
			}else{
				entity = new StaffEntity();
				BeanUtils.copyProperties(staffForm, entity);
				entity = staffDao.insert(entity);
			}
			if (entity != null) {
				result = 1;
			}
		} catch (Exception e) {
			logger.error("BStaffServiceImpl.addStaff发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BStaffServiceImpl.addStaff------------end-->");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteStaff(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.deleteStaff------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无人员信息");
				return map;
			}
			StaffEntity selectByPK = staffDao.selectByPK(id);
			if (selectByPK == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无人员信息");
				return map;
			}
			staffDao.deleteByPK(id);
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除失败");
			logger.error("BStaffServiceImpl.deleteStaff发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BStaffServiceImpl.deleteStaff------------end-->");
			}
		}
		return map;
	}

}
