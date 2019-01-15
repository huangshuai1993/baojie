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
import com.baojie.manage.back.baojie.dao.MaterialDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.dao.entity.MaterialEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.MaterialForm;
import com.baojie.manage.back.baojie.service.MaterialService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("materialService")
@Transactional
public class MaterialServiceImpl extends BaseService implements MaterialService {

	@Autowired
	private MaterialDao materialDao;

	@Autowired
	private TowerDao towerDao;
	
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Map<String,String>> valueOperations;

	@Autowired
	private ConfigDetailDao configDetailDao;
	@Override
	public PageResults<MaterialForm> getAllMaterial(Integer pageNumber, Integer pageSize, Long towerId,
			String beginTime, String endTime) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------MaterialServiceImpl.getAllMaterial------------begin-->");
		}
		PageResults<MaterialForm> page = new PageResults<>();
		try {
			List<MaterialEntity> materialList = materialDao.getMaterialList(pageNumber, pageSize, towerId,beginTime,endTime);
			PageInfo<MaterialEntity> pageInfo = new PageInfo<MaterialEntity>(materialList);
			if (!CollectionUtils.isEmpty(materialList)) {
				List<MaterialForm> list2 = BeanUtils.copyByList(materialList, MaterialForm.class);
				//获取配置项信息
				Map<String, String> materialType = getMaterialType();
				for (MaterialForm materialForm : list2) {
					materialForm.setTypeName(materialType.get(materialForm.getType().toString()));
				}
				page = new PageResults<MaterialForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			} else {
				page = new PageResults<MaterialForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
			}
		} catch (Exception e) {
			logger.error("MaterialServiceImpl.getAllMaterial发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------MaterialServiceImpl.getAllMaterial------------end-->");
			}
		}
		return page;
	}
	/**
	 * 获取缓存物料类型
	 * @return
	 */
	public Map<String, String> getMaterialType() {
		Map<String, String> materialType = valueOperations.get("materialType");
		if(materialType == null){
			List<ConfigDetailEntity> config = configDetailDao.queryConfigDetailByConfig("materialType");
			materialType = config.stream().collect(Collectors.toMap(o->o.getConfigValue().toString(), o->o.getConfigDetailDesc()));
			//放入缓存 同类型放入缓存 查询所有类型放入缓存
			valueOperations.set("materialType", materialType);
		}
		return materialType;
	}

	@Override
	public Integer addMaterial(MaterialForm materialForm) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------MaterialServiceImpl.addMaterial------------begin-->");
		}
		Integer result = 0;
		try {
			if (materialForm == null) {
				return result;
			}
			MaterialEntity entity = null;
			Integer i = null;
			if (materialForm.getMaterialId() != null) {
				entity = materialDao.queryById(materialForm.getMaterialId());
				if (entity.getTowerId() != materialForm.getTowerId()) {
					TowerEntity towerEntity = towerDao.queryById(materialForm.getTowerId());
					materialForm.setTowerName(towerEntity.getTowerName());
				}
				BeanUtils.copyPropertiesNotNUll(materialForm, entity);
				entity.setUpdated(new Date());
				i = materialDao.updateSelective(entity);
			} else {
				entity = new MaterialEntity();
				TowerEntity towerEntity = towerDao.queryById(materialForm.getTowerId());
				materialForm.setTowerName(towerEntity.getTowerName());
				BeanUtils.copyProperties(materialForm, entity);
				i = materialDao.saveSelective(entity);
			}
			if (i > 0) {
				result = 1;
			}
		} catch (Exception e) {
			logger.error("MaterialServiceImpl.addMaterial发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------MaterialServiceImpl.addMaterial------------end-->");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteMaterial(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------MaterialServiceImpl.deleteMaterial------------begin-->");
		}
		Map<String, Object> map = new HashMap<>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无物料信息");
				return map;
			}
			MaterialEntity selectByPK = materialDao.queryById(id);
			if (selectByPK == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "无物料信息");
				return map;
			}
			Integer i = materialDao.deleteById(id);
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
			logger.error("MaterialServiceImpl.deleteMaterial发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------MaterialServiceImpl.deleteMaterial------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getMaterialInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------MaterialServiceImpl.getMaterialInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "物料不存在");
				return map;
			}
			MaterialEntity materialEntity = materialDao.queryById(id);
			if (materialEntity == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "物料不存在");
				return map;
			}
			// 查询所有楼盘
			List<TowerEntity> list = towerDao.queryAll();
			map.put("towerList", list);
			map.put(Const.retCode, true);
			map.put("material", materialEntity);
			Map<String, String> materialType = getMaterialType();
			map.put("materialTypes", materialType);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "物料不存在");
			logger.error("MaterialServiceImpl.getMaterialInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------MaterialServiceImpl.getMaterialInfo------------end-->");
			}
		}
		return map;
	}

}
