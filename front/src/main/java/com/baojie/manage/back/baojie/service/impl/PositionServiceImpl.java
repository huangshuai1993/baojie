package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.service.PositionService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("positionService")
public class PositionServiceImpl extends BaseService implements PositionService {

	@Autowired
	private PositionDao positionDao;

	@Override
	public PageResults<PositionForm> getAllPosition(Integer pageNumber, Integer pageSize, String towerName)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------PositionServiceImpl.getAllPosition------------begin-->");
		}
		PageResults<PositionForm> page = new PageResults<>();
		try {
			PageResults<PositionEntity> positionList = positionDao.getPositionList(pageNumber, pageSize, towerName);
			if (positionList != null) {
				List<PositionEntity> list = positionList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<PositionForm> list2 = BeanUtils.copyByList(list, PositionForm.class);
					page = new PageResults<PositionForm>(list2, pageNumber, pageSize, positionList.getTotalCount());
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
				BeanUtils.copyPropertiesNotNUll(positionForm, entity);
				entity.setUpdated(new Date());
				entity = positionDao.update(entity);
			}else{
				entity = new PositionEntity();
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

}
