package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BStaffDao;
import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.back.baojie.service.BStaffService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.IdCardUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("bstaffService")
public class BStaffServiceImpl extends BaseService implements BStaffService {
	@Autowired
	private BStaffDao staffDao;
	@Autowired
	private BTowerDao towerDao;
	@Autowired
	private PositionDao positionDao;

	@Override
	public PageResults<StaffForm> getAllStaff(Integer pageNumber, Integer pageSize, Long towerId,String staffName) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.getAllStaff------------begin-->");
		}
		PageResults<StaffForm> response = new PageResults<StaffForm>();
		try {
			PageResults<StaffEntity> staffList = staffDao.getStaffList(pageNumber, pageSize, towerId, staffName);
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
			if (staffForm.getId() != null) {
				entity = staffDao.selectByPK(staffForm.getId());
				if (!staffForm.getTowerId().equals(entity.getTowerId())) {
					TowerEntity selectByPK = towerDao.selectByPK(staffForm.getTowerId());
					staffForm.setTowerName(selectByPK.getTowerName());
				}
				if (!staffForm.getPositionId().equals(entity.getPositionId())) {
					PositionEntity positionEntity = positionDao.selectByPK(staffForm.getPositionId());
					staffForm.setPositionName(positionEntity.getPositionName());
				}
				if (!staffForm.getIdCard().equals(entity.getIdCard())) {
					// 重新计算年龄
					int age = IdCardUtils.getAge(IdCardUtils.parse(staffForm.getBirthday()));
					staffForm.setAge(age);
				}
				BeanUtils.copyPropertiesNotNUll(staffForm, entity);
				entity.setUpdated(new Date());
				entity = staffDao.update(entity);
			} else {
				entity = new StaffEntity();
				TowerEntity selectByPK = towerDao.selectByPK(staffForm.getTowerId());
				staffForm.setTowerName(selectByPK.getTowerName());
				PositionEntity positionEntity = positionDao.selectByPK(staffForm.getPositionId());
				staffForm.setPositionName(positionEntity.getPositionName());
				// 重新计算年龄
				int age = IdCardUtils.getAge(IdCardUtils.parse(staffForm.getBirthday()));
				staffForm.setAge(age);
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

	@Override
	public Map<String, Object> getStaffInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.getStaffInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职员不存在");
				return map;
			}
			StaffEntity staffEntity = staffDao.selectByPK(id);
			if (staffEntity == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职员不存在");
				return map;
			}
			List<TowerEntity> list = towerDao.queryAll();
			map.put("towerList", list);
			map.put(Const.retCode, true);
			map.put("staff", staffEntity);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "职员不存在");
			logger.error("BStaffServiceImpl.getStaffInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BStaffServiceImpl.getStaffInfo------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getPositionListByTowerId(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.getPositionListByTowerId------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "楼盘id不能为空");
				return map;
			}
			List<PositionEntity> list = positionDao.getPositionListByTowerId(id);
			if (CollectionUtils.isEmpty(list)) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职称信息不存在");
				return map;
			}
			map.put("positionList", list);
			map.put(Const.retCode, true);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "职称信息不存在");
			logger.error("BStaffServiceImpl.getPositionListByTowerId发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BStaffServiceImpl.getPositionListByTowerId------------end-->");
			}
		}
		return map;
	}

}
