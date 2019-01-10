package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
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
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("positionService")
@Transactional
public class PositionServiceImpl extends BaseService implements PositionService {

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private TowerDao towerDao;

	@Override
	public PageResults<PositionForm> getAllPosition(Integer pageNumber, Integer pageSize, Long towerId)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.getAllPosition------------begin-->");
		}
		PageResults<PositionForm> page = new PageResults<>();
		try {
			List<PositionEntity> positionList = positionDao.getPositionList(pageNumber, pageSize, towerId);
			PageInfo<PositionEntity> pageInfo = new PageInfo<PositionEntity>(positionList);
			if (!CollectionUtils.isEmpty(positionList)) {
				List<PositionForm> list2 = BeanUtils.copyByList(positionList, PositionForm.class);
				page = new PageResults<PositionForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			} else {
				page = new PageResults<PositionForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
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
		Integer result = 0;
		try {
			if (positionForm == null) {
				return result;
			}
			PositionEntity entity = null;
			Integer i = null;
			if (positionForm.getPositionId() != null) {
				entity = positionDao.queryById(positionForm.getPositionId());
				if (entity.getTowerId() != positionForm.getTowerId()) {
					TowerEntity towerEntity = towerDao.queryById(positionForm.getTowerId());
					positionForm.setTowerName(towerEntity.getTowerName());
				}
				BeanUtils.copyPropertiesNotNUll(positionForm, entity);
				entity.setUpdated(new Date());
				i = positionDao.updateSelective(entity);
			} else {
				entity = new PositionEntity();
				TowerEntity towerEntity = towerDao.queryById(positionForm.getTowerId());
				positionForm.setTowerName(towerEntity.getTowerName());
				BeanUtils.copyProperties(positionForm, entity);
				i = positionDao.saveSelective(entity);
			}
			if (i > 0) {
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
			PositionEntity selectByPK = positionDao.queryById(id);
			if (selectByPK == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无职称信息");
				return map;
			}
			Integer i = positionDao.deleteById(id);
			if (i > 0) {
				map.put(Const.retCode, true);
				map.put(Const.retMsg, "删除成功!");
			} else {
				map.put(Const.retCode, true);
				map.put(Const.retMsg, "删除失败");
			}
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
			PositionEntity positionEntity = positionDao.queryById(id);
			if (positionEntity == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "职务不存在");
				return map;
			}
			// 查询所有楼盘
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
