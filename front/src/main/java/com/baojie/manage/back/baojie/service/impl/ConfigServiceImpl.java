package com.baojie.manage.back.baojie.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baojie.manage.back.baojie.dao.ConfigDao;
import com.baojie.manage.back.baojie.dao.ConfigDetailDao;
import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.dao.entity.ConfigEntity;
import com.baojie.manage.back.baojie.form.ConfigDetailForm;
import com.baojie.manage.back.baojie.form.ConfigForm;
import com.baojie.manage.back.baojie.service.ConfigService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("configService")
@Transactional
public class ConfigServiceImpl extends BaseService implements ConfigService {

	@Autowired
	private ConfigDao configDao;
	
	@Autowired
	private ConfigDetailDao configDetailDao;
	@Override
	public PageResults<ConfigForm> getAllConfig(Integer pageNumber, Integer pageSize, String configDesc) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.getAllConfig------------begin-->");
		}
		PageResults<ConfigForm> page = new PageResults<>();
		try {
			List<ConfigEntity> allConfig = configDao.getAllConfig(pageNumber, pageSize, configDesc);
			PageInfo<ConfigEntity> pageInfo = new PageInfo<ConfigEntity>(allConfig);
			if(!CollectionUtils.isEmpty(allConfig)){
				List<ConfigForm> list2 = BeanUtils.copyByList(allConfig, ConfigForm.class);
				page = new PageResults<ConfigForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			}else{
				page = new PageResults<ConfigForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
			}
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.getAllConfig发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.getAllConfig------------end-->");
			}
		}
		return page;
	}
	@Override
	public PageResults<ConfigDetailForm> getAllConfigDetail(Integer pageNumber, Integer pageSize, String configuration,String configDetailDesc)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.getAllConfigDetail------------begin-->");
		}
		PageResults<ConfigDetailForm> page = new PageResults<ConfigDetailForm>();
		try {
			List<ConfigDetailEntity> allConfig = configDetailDao.getAllConfigDetail(pageNumber, pageSize, configuration,configDetailDesc);
			PageInfo<ConfigDetailEntity> pageInfo = new PageInfo<ConfigDetailEntity>(allConfig);
			if(!CollectionUtils.isEmpty(allConfig)){
				List<ConfigDetailForm> list2 = BeanUtils.copyByList(allConfig, ConfigDetailForm.class);
				page = new PageResults<ConfigDetailForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			}else{
				page = new PageResults<ConfigDetailForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
			}
			
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.getAllConfigDetail发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.getAllConfigDetail------------end-->");
			}
		}
		return page;
	}
	@Override
	public Map<String, Object> addOrUpdateConfig(ConfigForm configForm) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.addOrUpdateConfig------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(configForm == null){
				map.put(Const.retCode, Boolean.FALSE);
				map.put(Const.retMsg, "操作失败!");
				return map;
			}
			ConfigEntity entity = null;
			Integer i = null ;
			if(configForm.getId() != null){
				entity = configDao.queryById(configForm.getId());
				BeanUtils.copyPropertiesNotNUll(configForm, entity);
				i = configDao.updateSelective(entity);
			}else{
				entity = new ConfigEntity();
				BeanUtils.copyProperties(configForm, entity);
				i = configDao.saveSelective(entity);
			}
			if(i != 1){
				map.put(Const.retCode, Boolean.FALSE);
				map.put(Const.retMsg, "操作失败!");
			}
			map.put(Const.retCode, Boolean.TRUE);
			map.put(Const.retMsg, "操作成功!");
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.addOrUpdateConfig发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.addOrUpdateConfig------------end-->");
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> addOrUpdateConfigDetail(ConfigDetailForm configDetailForm) throws BizException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.addOrUpdateConfigDetail------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(configDetailForm == null){
				map.put(Const.retCode, Boolean.FALSE);
				map.put(Const.retMsg, "操作失败!");
				return map;
			}
			ConfigDetailEntity entity = null;
			Integer i = null ;
			if(configDetailForm.getId() != null){
				entity = configDetailDao.queryById(configDetailForm.getId());
				BeanUtils.copyPropertiesNotNUll(configDetailForm, entity);
				i = configDetailDao.updateSelective(entity);
			}else{
				entity = new ConfigDetailEntity();
				BeanUtils.copyProperties(configDetailForm, entity);
				i = configDetailDao.saveSelective(entity);
			}
			if(i != 1){
				map.put(Const.retCode, Boolean.FALSE);
				map.put(Const.retMsg, "操作失败!");
			}
			map.put(Const.retCode, Boolean.TRUE);
			map.put(Const.retMsg, "操作成功!");
			
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.addOrUpdateConfigDetail发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.addOrUpdateConfigDetail------------end-->");
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> getInfoConfig(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.getInfoConfig------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "配置不存在");
	            return map;
	        }
			ConfigEntity configEntity = configDao.queryById(id);
			if(configEntity == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "配置不存在");
	            return map;
			}
			map.put(Const.retCode, true);
			map.put("config", configEntity);
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.getInfoConfig发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.getInfoConfig------------end-->");
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> getInfoConfigDetail(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.getInfoConfigDetail------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "配置不存在");
	            return map;
	        }
			ConfigDetailEntity queryById = configDetailDao.queryById(id);
			if(queryById == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "楼盘不存在");
	            return map;
			}
			map.put(Const.retCode, true);
			map.put("configDetail", queryById);
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.getInfoConfigDetail发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.getInfoConfigDetail------------end-->");
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> deleteConfig(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.deleteConfig------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无配置信息");
                return map;
			}
			ConfigEntity config = configDao.queryById(id);
			if(config == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无配置信息");
                return map;
			}
			int i = configDetailDao.queryConfigDetailByConfigCount(config.getConfiguration());
			if(i > 0){
				map.put(Const.retCode, false);
	            map.put(Const.retMsg, "配置项下包含配置明细，请先删除配置明细!");
	            return map;
			}
			configDao.deleteById(id);
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.deleteConfig发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.deleteConfig------------end-->");
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> deleteConfigDetail(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.deleteConfigDetail------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无配置明细信息");
                return map;
			}
			ConfigDetailEntity config = configDetailDao.queryById(id);
			if(config == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无配置明细信息");
                return map;
			}
			configDetailDao.deleteById(id);
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.deleteConfigDetail发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.deleteConfigDetail------------end-->");
			}
		}
		return map;
	}
	@Override
	public List<ConfigForm> queryConfigAll() throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------ConfigServiceImpl.queryAll------------begin-->");
		}
		List<ConfigForm> list = new ArrayList<ConfigForm>();
		try {
			List<ConfigEntity> queryAll = configDao.queryAll();
			if(CollectionUtils.isNotEmpty(queryAll)){
				list = BeanUtils.copyByList(queryAll, ConfigForm.class);
			}
			
		} catch (Exception e) {
			logger.error("ConfigServiceImpl.queryAll发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------ConfigServiceImpl.queryAll------------end-->");
			}
		}
		return list;
	}
	

}
