package com.baojie.manage.back.baojie.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.StaffDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.BigDecimalUtils;
import com.baojie.manage.base.common.util.DateUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("salaryService")
@Transactional
public class SalaryServiceImpl extends BaseService implements SalaryService {
	@Autowired
	private SalaryDao salaryDao;
	@Autowired
	private TowerDao towerDao;
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.getAllSalary------------begin-->");
		}
		PageResults<SalaryForm> page = new PageResults<SalaryForm>();
		try {
			
			List<SalaryEntity> allSalary = salaryDao.getAllSalary(pageNumber, pageSize, towerId, searchName, time);
			PageInfo<SalaryEntity> pageInfo = new PageInfo<SalaryEntity>(allSalary);
			if (!CollectionUtils.isEmpty(allSalary)) {
				List<SalaryForm> list2 = BeanUtils.copyByList(allSalary, SalaryForm.class);
				page = new PageResults<SalaryForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			}else{
				page = new PageResults<SalaryForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
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

	@Override
	@Transactional
	public Integer addSalaryMonth() throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.addSalaryMonth------------begin-->");
		}
		Integer response = 0;
		try {
			//获取当前时间的年月  2018-12
			String date = DateUtil.getDateString(new Date(), DateUtil.DATESHOWFORMAT).substring(0,7);
			//查询是否已经生成过当月工资
			int count = salaryDao.queryCountSalaryByMonth(date);
			if(count > 1){
				return 2;
			}
			//查询所有楼盘  按楼盘生成员工工资
			List<TowerEntity> allTower = towerDao.queryAll();
			if(CollectionUtils.isEmpty(allTower)){
				return response;
			}
			for (TowerEntity tower : allTower) {
				//校验
				//查询楼盘下员工   职称
				List<PositionEntity> positionList = positionDao.getPositionListByTowerId(tower.getTowerId());
				if(CollectionUtils.isEmpty(positionList)){
					return 3;
				}
				Map<Long,PositionEntity> map = positionList.stream().collect(Collectors.toMap(o->o.getPositionId(), i->i));
				//查询所有楼盘下人员
				List<StaffEntity> staffByTowerId = staffDao.getStaffByTowerId(tower.getTowerId());
				if(CollectionUtils.isEmpty(staffByTowerId)){
					return response;
				}
			}
			for (TowerEntity tower : allTower) {
				//生成
				//查询楼盘下员工   职称
				List<PositionEntity> positionList = positionDao.getPositionListByTowerId(tower.getTowerId());
				Map<Long,PositionEntity> map = positionList.stream().collect(Collectors.toMap(o->o.getPositionId(), i->i));
				//查询所有楼盘下人员
				List<StaffEntity> staffByTowerId = staffDao.getStaffByTowerId(tower.getTowerId());
				SalaryEntity entity = new SalaryEntity();
				for (StaffEntity staff : staffByTowerId) {
					entity.setStaffId(staff.getId());
					entity.setStaffName(staff.getName());
					entity.setTowerId(staff.getTowerId());
					entity.setTowerName(staff.getTowerName());
					entity.setPositionId(staff.getPositionId());
					entity.setPositionName(staff.getPositionName());
					PositionEntity position = map.get(staff.getPositionId());
					entity.setBasePay(position.getBasePay());
					entity.setAllowance(position.getAllowance());
					entity.setSendPay(BigDecimalUtils.add(position.getBasePay(),position.getAllowance()));
					entity.setRealPay(BigDecimalUtils.add(position.getBasePay(),position.getAllowance()));
					entity.setSalaryMonth(date);
					salaryDao.saveSelective(entity);
				}
			}
			
			response = 1;
		} catch (Exception e) {
			logger.error("SalaryServiceImpl.addSalaryMonth发生异常", e);
			throw new Exception("方法异常");
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.addSalaryMonth------------end-->");
			}
		}
		return response;
	}

	@Override
	public Map<String, Object> getSalaryInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.getSalaryInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "id不能为空");
				return map;
			}
			SalaryEntity salaryEntity = salaryDao.queryById(id);
			if(salaryEntity == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "工资信息不存在");
				return map;
			}
			map.put(Const.retCode, true);
			map.put("salary", salaryEntity);
		} catch (Exception e) {
			logger.error("SalaryServiceImpl.getSalaryInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.getSalaryInfo------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> updateStaffSalary(SalaryForm salary) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.updateStaffSalary------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//判断
			if(salary == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "工资信息不存在");
				return map;
			}
			//查询数据
			SalaryEntity entity = salaryDao.queryById(salary.getId());
			BeanUtils.copyPropertiesNotNUll(salary, entity);
			//累计  加项
			BigDecimal basePay1 = BigDecimalUtils.add(entity.getBasePay(), entity.getAllowance());
			double div = BigDecimalUtils.div(basePay1.doubleValue(), new Double("30"));//每日工资基数
			double mul = BigDecimalUtils.mul(div,new Double(entity.getWorkDay().toString()));//基本工资数
			//小数点2位
			double round = BigDecimalUtils.round(mul, 2);
			BigDecimal basePay = new BigDecimal(Double.toString(round));
			// 病事假
			BigDecimal askForLeave = BigDecimalUtils.sub(basePay1, basePay);
			// 应发工资
			BigDecimal sendPay = BigDecimalUtils.add(BigDecimalUtils.add(entity.getOvertimePay(), entity.getHoliday()),BigDecimalUtils.add(entity.getOther(), basePay));
			//累计 减项
			// 扣款合计
			BigDecimal deductTotalPay = BigDecimalUtils.add(BigDecimalUtils.add(askForLeave, entity.getOtherDeductPay()), BigDecimalUtils.add(entity.getPersonTax(), entity.getSocialSecurity()));
			//实发工资
			BigDecimal realPay = BigDecimalUtils.sub(sendPay, deductTotalPay);
			
			entity.setRealPay(realPay);
			entity.setDeductTotalPay(deductTotalPay);
			entity.setAskForLeave(askForLeave);
			entity.setSendPay(sendPay);
			entity.setUpdated(new Date());
			Integer i = salaryDao.updateSelective(entity);
			if(i > 0){
				map.put(Const.retCode, true);
				map.put(Const.retMsg, "操作成功");
				
			}else{
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "工资信息修改失败");
			}
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "工资信息修改失败");
			logger.error("SalaryServiceImpl.updateStaffSalary发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.updateStaffSalary------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteSalary(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.deleteSalary------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无工资信息");
				return map;
			}
			SalaryEntity salaryEntity = salaryDao.queryById(id);
			if (salaryEntity == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无工资信息");
				return map;
			}
			salaryDao.deleteById(id);
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除失败");
			logger.error("SalaryServiceImpl.deleteSalary发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.deleteSalary------------end-->");
			}
		}
		return map;
	}
}
