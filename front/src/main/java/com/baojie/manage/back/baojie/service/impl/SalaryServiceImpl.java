package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BStaffDao;
import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.DateUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("salaryService")
public class SalaryServiceImpl extends BaseService implements SalaryService {

	@Autowired
	private SalaryDao salaryDao;
	@Autowired
	private BTowerDao towerDao;
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private BStaffDao staffDao;
	@Override
	public PageResults<SalaryForm> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.getAllSalary------------begin-->");
		}
		PageResults<SalaryForm> page = new PageResults<SalaryForm>();
		try {
			PageResults<SalaryEntity> allSalary = salaryDao.getAllSalary(pageNumber, pageSize, towerId, searchName,time);
			if (allSalary != null) {
				List<SalaryEntity> list = allSalary.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<SalaryForm> list2 = BeanUtils.copyByList(list, SalaryForm.class);
					page = new PageResults<SalaryForm>(list2, pageNumber, pageSize, allSalary.getTotalCount());
				}
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
	public Integer addSalaryMonth() throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------SalaryServiceImpl.addSalaryMonth------------begin-->");
		}
		Integer response = 0;
		try {
			//获取当前时间的年月  2018-12
			String date = DateUtil.getDateString(new Date(), DateUtil.DATESHOWFORMAT).substring(0,7);
			//查询是否已经生成过当月工资
			long count = salaryDao.queryCountSalaryByMonth(date);
			if(count < 1){
				return 2;
			}
			//查询所有楼盘  按楼盘生成员工工资
			List<TowerEntity> allTower = towerDao.queryAll();
			if(CollectionUtils.isEmpty(allTower)){
				return response;
			}
			for (TowerEntity tower : allTower) {
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
			
		} catch (Exception e) {
			logger.error("SalaryServiceImpl.addSalaryMonth发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------SalaryServiceImpl.addSalaryMonth------------end-->");
			}
		}
		return response;
	}
}
