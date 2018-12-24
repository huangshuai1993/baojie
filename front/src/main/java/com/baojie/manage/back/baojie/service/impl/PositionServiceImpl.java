package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.service.PositionService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.google.common.collect.Lists;

@Service("positionService")
public class PositionServiceImpl extends BaseService implements PositionService {

	@Autowired
	private PositionDao positionDao;
	@Autowired
	private BTowerDao towerDao;

	@Override
	public PageResults<PositionForm> getAllPosition(Integer pageNumber, Integer pageSize, Long towerId)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.getAllPosition------------begin-->");
		}
		PageResults<PositionForm> page = new PageResults<>();
		try {
			PageResults<PositionEntity> positionList = positionDao.getPositionList(pageNumber, pageSize, towerId);
			if (positionList != null) {
				List<PositionEntity> list = positionList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<PositionForm> list2 = BeanUtils.copyByList(list, PositionForm.class);
					page = new PageResults<PositionForm>(list2, pageNumber, pageSize, positionList.getTotalCount());
				}else{
					page = new PageResults<PositionForm>(Lists.newArrayList(), pageNumber, pageSize, positionList.getTotalCount());
				}
			}
		} catch (Exception e) {
			logger.error("PositionServiceImpl.deletePosition发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------PositionServiceImpl.getAllPosition------------end-->");
			}
		}
		return page;
	}

	@Override
	public Integer addPosition(PositionForm positionForm) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.addPosition------------begin-->");
		}
		Integer result  = 0;
		try {
			if(positionForm == null){
				return  result;
			}
			PositionEntity entity = new PositionEntity();
			if(positionForm.getPositionId() != null){
				entity = positionDao.selectByPK(positionForm.getPositionId());
				if(entity.getTowerId() != positionForm.getTowerId()){
					TowerEntity towerEntity = towerDao.selectByPK(positionForm.getTowerId());
					positionForm.setTowerName(towerEntity.getTowerName());
				}
				BeanUtils.copyPropertiesNotNUll(positionForm, entity);
				entity.setUpdated(new Date());
				entity = positionDao.update(entity);
			}else{
				entity = new PositionEntity();
				TowerEntity towerEntity = towerDao.selectByPK(positionForm.getTowerId());
				positionForm.setTowerName(towerEntity.getTowerName());
				BeanUtils.copyProperties(positionForm, entity);
				entity = positionDao.insert(entity);
			}
			if(entity!=null){
				result = 1;
			}
		} catch (Exception e) {
			logger.error("PositionServiceImpl.deletePosition发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------PositionServiceImpl.addPosition------------end-->");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deletePosition(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.deletePosition------------begin-->");
		}
		Map<String, Object> map = new HashMap<>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无职称信息");
				return map;
			}
			PositionEntity selectByPK = positionDao.selectByPK(id);
			if (selectByPK == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无职称信息");
				return map;
			}
			positionDao.deleteByPK(id);
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "删除失败");
			logger.error("PositionServiceImpl.deletePosition发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------PositionServiceImpl.deletePosition------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getPositionInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.getPositionInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职务不存在");
				return map;
			}
			PositionEntity positionEntity = positionDao.selectByPK(id);
			if (positionEntity == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职务不存在");
				return map;
			}
			//查询所有楼盘
			List<TowerEntity> list = towerDao.queryAll();
			map.put("towerList", list);
			map.put(Const.retCode, true);
			map.put("position", positionEntity);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "职务不存在");
			logger.error("PositionServiceImpl.getPositionInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------PositionServiceImpl.getPositionInfo------------end-->");
			}
		}
		return map;
	}

}
