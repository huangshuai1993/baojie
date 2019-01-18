package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baojie.manage.back.baojie.dao.ConfigDetailDao;
import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.StaffDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.back.baojie.form.enums.GenderEnum;
import com.baojie.manage.back.baojie.service.BStaffService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.IdCardUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("bstaffService")
@Transactional
public class BStaffServiceImpl extends BaseService implements BStaffService {
	@Autowired
	private TowerDao towerDao;
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private PositionDao positionDao;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Map<String,String>> valueOperations;
	
	@Autowired
	private ConfigDetailDao configDetailDao;
	@Override
	public PageResults<StaffForm> getAllStaff(Integer pageNumber, Integer pageSize, Long towerId, String staffName,Integer status,Integer startAge,Integer endAge,Integer gender)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BStaffServiceImpl.getAllStaff------------begin-->");
		}
		PageResults<StaffForm> response = new PageResults<StaffForm>();
		try {
			List<StaffEntity> staffList = staffDao.getStaffList(pageNumber, pageSize, towerId, staffName, status, startAge, endAge, gender);
			PageInfo<StaffEntity> pageInfo = new PageInfo<StaffEntity>(staffList);
			if (!CollectionUtils.isEmpty(staffList)) {
				List<StaffForm> list2 = BeanUtils.copyByList(staffList, StaffForm.class);
				Map<String, String> staffStatusType = getStaffStatusType();
				for (StaffForm staffForm : list2) {
					staffForm.setStatusName(staffStatusType.get(staffForm.getStatus()+""));
					staffForm.setGenderName(GenderEnum.getName(staffForm.getGender()));
				}
				response = new PageResults<StaffForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			} else {
				response = new PageResults<StaffForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
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

	/**
	 * 员工状态
	 * @return
	 */
	public Map<String, String> getStaffStatusType() {
		Map<String, String> staffStatus = valueOperations.get("staffStatus");
		if(staffStatus == null){
			List<ConfigDetailEntity> config = configDetailDao.queryConfigDetailByConfig("staffStatus");
			staffStatus = config.stream().collect(Collectors.toMap(o->o.getConfigValue().toString(), o->o.getConfigDetailDesc()));
			//放入缓存 同类型放入缓存 查询所有类型放入缓存
			valueOperations.set("staffStatus", staffStatus);
		}
		return staffStatus;
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
			Integer i = null;
			if (staffForm.getId() != null) {
				entity = staffDao.queryById(staffForm.getId());
				if (!staffForm.getTowerId().equals(entity.getTowerId())) {
					TowerEntity selectByPK = towerDao.queryById(staffForm.getTowerId());
					staffForm.setTowerName(selectByPK.getTowerName());
				}
				if (!staffForm.getPositionId().equals(entity.getPositionId())) {
					PositionEntity positionEntity = positionDao.queryById(staffForm.getPositionId());
					staffForm.setPositionName(positionEntity.getPositionName());
				}
				if (!staffForm.getIdCard().equals(entity.getIdCard())) {
					// 重新计算年龄
					int age = IdCardUtils.getAge(IdCardUtils.parse(staffForm.getBirthday()));
					staffForm.setAge(age);
				}
				BeanUtils.copyPropertiesNotNUll(staffForm, entity);
				entity.setUpdated(new Date());
				i = staffDao.updateSelective(entity);
				//更新楼盘实际人数
			} else {
				entity = new StaffEntity();
				TowerEntity selectByPK = towerDao.queryById(staffForm.getTowerId());
				staffForm.setTowerName(selectByPK.getTowerName());
				PositionEntity positionEntity = positionDao.queryById(staffForm.getPositionId());
				staffForm.setPositionName(positionEntity.getPositionName());
				// 重新计算年龄
				int age = IdCardUtils.getAge(IdCardUtils.parse(staffForm.getBirthday()));
				staffForm.setAge(age);
				BeanUtils.copyProperties(staffForm, entity);
				i = staffDao.saveSelective(entity);
			}
			if (i > 0) {
				result = 1;
				//更新楼盘实际员工数
				StaffEntity record = new StaffEntity();
				record.setTowerId(entity.getTowerId());
				Integer queryCount = staffDao.queryCount(record);
				TowerEntity towerRecord = new TowerEntity();
				towerRecord.setTowerId(entity.getTowerId());
				towerRecord.setVirtualCount(queryCount);
				towerDao.updateSelective(towerRecord);
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
			StaffEntity selectByPK = staffDao.queryById(id);
			if (selectByPK == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无人员信息");
				return map;
			}
			Integer i = staffDao.deleteById(id);
			if (i > 0) {
				//更新楼盘实际员工数
				StaffEntity record = new StaffEntity();
				record.setTowerId(selectByPK.getTowerId());
				Integer queryCount = staffDao.queryCount(record);
				TowerEntity towerRecord = new TowerEntity();
				towerRecord.setTowerId(selectByPK.getTowerId());
				towerRecord.setVirtualCount(queryCount);
				towerDao.updateSelective(towerRecord);
				map.put(Const.retCode, true);
				map.put(Const.retMsg, "删除成功!");
			} else {
				map.put(Const.retCode, true);
				map.put(Const.retMsg, "删除失败");
			}

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
			StaffEntity staffEntity = staffDao.queryById(id);
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
	
	public Integer getAverAge(Long towerId){
		return staffDao.getAverAge(towerId);
	}

}
