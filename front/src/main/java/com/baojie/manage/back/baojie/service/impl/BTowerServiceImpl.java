package com.baojie.manage.back.baojie.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baojie.manage.back.baojie.dao.ContractDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.ContractEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("btowerService")
@Transactional
public class BTowerServiceImpl extends BaseService implements BTowerService {
	@Autowired
	private TowerDao towerDao;

	@Autowired
	private ContractDao contractDao;
	@Override
	public PageResults<TowerForm> getAllTower(Integer pageNumber, Integer pageSize, String towerName, String functionaryName,String beginTime,String endTime)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.getAllTower------------begin-->");
		}
		PageResults<TowerForm> page = new PageResults<>();
		try {
			List<TowerEntity> towerList = towerDao.getTowerList(pageNumber, pageSize, towerName, functionaryName, beginTime, endTime);
			PageInfo<TowerEntity> pageInfo = new PageInfo<TowerEntity>(towerList);
			if(!CollectionUtils.isEmpty(towerList)){
				List<TowerForm> list2 = BeanUtils.copyByList(towerList, TowerForm.class);
				page = new PageResults<TowerForm>(list2, pageNumber, pageSize, pageInfo.getTotal());
			}else{
				page = new PageResults<TowerForm>(Lists.newArrayList(), pageNumber, pageSize, pageInfo.getTotal());
			}
		} catch (Exception e) {
			logger.error("BTowerServiceImpl.getAllTower发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BTowerServiceImpl.getAllTower------------end-->");
			}
		}
		return page;
	}

	@Override
	public Integer addTower(TowerForm towerForm) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.addTower------------begin-->");
		}
		Integer result  = 0;
		try {
			if(towerForm == null){
				return  result;
			}
			TowerEntity entity = null;
			Integer i = null ;
			if(towerForm.getTowerId() != null){
				entity = towerDao.queryById(towerForm.getTowerId());
				BeanUtils.copyPropertiesNotNUll(towerForm, entity);
				entity.setUpdated(new Date());
				if(towerForm.getContractId() != null){
					ContractEntity contractEntity = contractDao.queryById(towerForm.getContractId());
					entity.setPeopleCount(contractEntity.getPeopleCount());
					contractEntity.setTowerId(entity.getTowerId());
					contractEntity.setTowerName(entity.getTowerName());
					contractDao.updateSelective(contractEntity);
				}
				i = towerDao.updateSelective(entity);
			}else{
				entity = new TowerEntity();
				BeanUtils.copyProperties(towerForm, entity);
				if(towerForm.getContractId() != null){
					ContractEntity contractEntity = contractDao.queryById(towerForm.getContractId());
					entity.setPeopleCount(contractEntity.getPeopleCount());
					contractEntity.setTowerId(entity.getTowerId());
					contractEntity.setTowerName(entity.getTowerName());
					contractDao.updateSelective(contractEntity);
				}
				i = towerDao.saveSelective(entity);
			}
			if(i > 0){
				result = 1;
			}
		} catch (Exception e) {
			logger.error("BTowerServiceImpl.addTower发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BTowerServiceImpl.addTower------------end-->");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteTower(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.deleteTower------------begin-->");
		}
		 Map<String, Object> map = new HashMap<>();
		try {
			if(id == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无楼盘信息");
                return map;
			}
			TowerEntity tower = towerDao.queryById(id);
			if(tower == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无楼盘信息");
                return map;
			}
			towerDao.deleteById(id);
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
		} catch (Exception e) {
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除失败");
			logger.error("BTowerServiceImpl.deleteTower发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BTowerServiceImpl.deleteTower------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getTowerInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.getTowerInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "楼盘不存在");
	            return map;
	        }
			TowerEntity tower = towerDao.queryById(id);
			if(tower == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "楼盘不存在");
	            return map;
			}
			map.put(Const.retCode, true);
			map.put("tower", tower);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "楼盘不存在");
			logger.error("BTowerServiceImpl.getTowerInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BTowerServiceImpl.getTowerInfo------------end-->");
			}
		}
		return map;
	}

	@Override
	public List<TowerForm> queryAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.queryAll------------begin-->");
		}
		List<TowerForm> list = new ArrayList<>();
		try {
			List<TowerEntity> listEntity = towerDao.queryAll();
			if(!CollectionUtils.isEmpty(listEntity)){
				list = BeanUtils.copyByList(listEntity, TowerForm.class);
			}
		} catch (Exception e) {
			logger.error("BTowerServiceImpl.queryAll发生异常", e);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BTowerServiceImpl.queryAll------------end-->");
			}
		}
		return list;
	}

}
