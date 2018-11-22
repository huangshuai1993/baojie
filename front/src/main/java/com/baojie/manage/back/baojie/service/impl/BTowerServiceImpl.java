package com.baojie.manage.back.baojie.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dao.BTowerDao;
import com.baojie.manage.back.baojie.dao.Entity.ContractEntity;
import com.baojie.manage.back.baojie.dao.Entity.TowerEntity;
import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("btowerService")
public class BTowerServiceImpl extends BaseService implements BTowerService {
	
	@Autowired
	private BTowerDao towerDao;

	@Override
	public PageResults<TowerForm> getAllTower(Integer pageNumber, Integer pageSize, String towerName, String functionaryName)
			throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BTowerServiceImpl.getAllTower------------begin-->");
		}
		PageResults<TowerForm> page = new PageResults<>();
		try {
			PageResults<TowerEntity> towerList = towerDao.getTowerList(pageNumber, pageSize, towerName, functionaryName);
			if (towerList != null) {
				List<TowerEntity> list = towerList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<TowerForm> list2 = BeanUtils.copyByList(list, TowerForm.class);
					page = new PageResults<TowerForm>(list2, pageNumber, pageSize, towerList.getTotalCount());
				}
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
			TowerEntity entity = new TowerEntity();
			BeanUtils.copyProperties(towerForm, entity);
			TowerEntity save = towerDao.save(entity);
			if(save!=null){
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
			TowerEntity tower = towerDao.selectByPK(id);
			if(tower == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无楼盘信息");
                return map;
			}
			towerDao.deleteByPK(id);
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

}
