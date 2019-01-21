package com.baojie.manage.back.baojie.service.impl;

import java.util.ArrayList;
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
import com.baojie.manage.back.baojie.dao.ContractDao;
import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.dao.entity.ContractEntity;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("bcontractService")
@Transactional
public class BContractServiceImpl extends BaseService implements BContractService {

	@Autowired
	private ContractDao contractDao;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Map<String,String>> valueOperations;
	
	@Autowired
	private ConfigDetailDao configDetailDao;
	@Autowired
	private TowerDao towerDao;
	@Override
	public PageResults<ContractForm> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getAllContract------------begin-->");
		}
		PageResults<ContractForm> page = new PageResults<>();
		try {
			List<ContractEntity> contractList = contractDao.getContractList(pageNumber, pageSize, contractName, towerName, status);
			PageInfo<ContractEntity> contractPageList = new PageInfo<ContractEntity>(contractList);
			if(!CollectionUtils.isEmpty(contractList)){
				List<ContractEntity> list = contractPageList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<ContractForm> list2 = BeanUtils.copyByList(list, ContractForm.class);
					//获取配置项信息
					Map<String, String> contractType = getContractType();
					Map<String, String> contractDetailType = getContractDetailType();
					Map<String, String> contractStatus = getContractStatus();
					for (ContractForm contractForm : list2) {
						contractForm.setTypeName(contractType.get(contractForm.getType()+""));
						contractForm.setDetailTypeName(contractDetailType.get(contractForm.getDetailType()+""));
						contractForm.setStatusName(contractStatus.get(contractForm.getStatus()+""));
					}
					page = new PageResults<ContractForm>(list2, pageNumber, pageSize, contractPageList.getTotal());
				}
			}else{
				page = new PageResults<ContractForm>(Lists.newArrayList(), pageNumber, pageSize, contractPageList.getTotal());
			}
		} catch (Exception e) {
			logger.error("BContractServiceImpl.getAllContract发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getAllContract------------end-->");
			}
		}
		return page;
	}
	/**
	 * 合同状态
	 * @return
	 */
	public Map<String, String> getContractStatus() {
		Map<String, String> contractStatus = valueOperations.get("contractStatus");
		if(contractStatus == null){
			List<ConfigDetailEntity> config = configDetailDao.queryConfigDetailByConfig("contractStatus");
			contractStatus = config.stream().collect(Collectors.toMap(o->o.getConfigValue().toString(), o->o.getConfigDetailDesc()));
			//放入缓存 同类型放入缓存 查询所有类型放入缓存
			valueOperations.set("contractStatus", contractStatus);
		}
		return contractStatus;
	}
	/**
	 * 合同明细类型
	 * @return
	 */
	public Map<String, String> getContractDetailType() {
		Map<String, String> contractDetailType = valueOperations.get("contractDetailType");
		if(contractDetailType == null){
			List<ConfigDetailEntity> config = configDetailDao.queryConfigDetailByConfig("contractDetailType");
			contractDetailType = config.stream().collect(Collectors.toMap(o->o.getConfigValue().toString(), o->o.getConfigDetailDesc()));
			//放入缓存 同类型放入缓存 查询所有类型放入缓存
			valueOperations.set("contractDetailType", contractDetailType);
		}
		return contractDetailType;
	}
	/**
	 * 合同类型
	 * @return
	 */
	public Map<String, String> getContractType() {
		Map<String, String> contractType = valueOperations.get("contractType");
		if(contractType == null){
			List<ConfigDetailEntity> config = configDetailDao.queryConfigDetailByConfig("contractType");
			contractType = config.stream().collect(Collectors.toMap(o->o.getConfigValue().toString(), o->o.getConfigDetailDesc()));
			//放入缓存 同类型放入缓存 查询所有类型放入缓存
			valueOperations.set("contractType", contractType);
		}
		return contractType;
	}

	public void example() {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getAllContract------------begin-->");
		}
		try {

		} catch (Exception e) {
			logger.error("BContractServiceImpl.getAllContract发生异常", e);
			// throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getAllContract------------end-->");
			}
		}
	}

	@Override
	public Integer addContract(ContractForm contract) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.addContract------------begin-->");
		}
		Integer result  = 0;
		try {
			if(contract == null){
				return  result;
			}
			ContractEntity entity = null;
			Integer i = null;
			boolean flag = false;
			if(contract.getId() != null){
				entity = contractDao.queryById(contract.getId());
				if(!entity.getPeopleCount().equals(contract.getPeopleCount())){
					flag = true;
				}
				BeanUtils.copyPropertiesNotNUll(contract, entity);
				entity.setUpdated(new Date());
				i = contractDao.updateSelective(entity);
			}else{
				entity = new ContractEntity();
				BeanUtils.copyProperties(contract, entity);
				i = contractDao.saveSelective(entity);
			}
			if(i >0){
				result = 1;
				//判断合同人数是否更改
				if(flag){
					//更新楼盘
					TowerEntity tower = new TowerEntity();
					tower.setContractId(contract.getId());
					List<TowerEntity> list = towerDao.queryListByWhere(tower);
					if(CollectionUtils.isNotEmpty(list)){
						for (TowerEntity towerEntity : list) {
							towerEntity.setPeopleCount(contract.getPeopleCount());
							towerDao.updateSelective(towerEntity);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("BContractServiceImpl.addContract发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.addContract------------end-->");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteContract(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.deleteContract------------begin-->");
		}
		 Map<String, Object> map = new HashMap<>();
		try {
			if(id == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无合同信息");
                return map;
			}
			ContractEntity entity = contractDao.queryById(id);
			if(entity == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无合同信息");
                return map;
			}
			Integer i = contractDao.deleteById(id);
			if(i>0){
				map.put(Const.retCode, true);
	            map.put(Const.retMsg, "删除成功!");
			}else{
				map.put(Const.retCode, true);
	            map.put(Const.retMsg, "删除失败");
			}
		} catch (Exception e) {
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除失败");
			logger.error("BContractServiceImpl.deleteContract发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.deleteContract------------end-->");
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getContractInfo(Long id) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getContractInfo------------begin-->");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "合同不存在");
	            return map;
	        }
			ContractEntity contract = contractDao.queryById(id);
			if(contract == null){
				map.put(Const.retCode, false);
				map.put(Const.retMsg, "合同不存在");
	            return map;
			}
			map.put(Const.retCode, true);
			map.put("contract", contract);
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "合同不存在");
			logger.error("BContractServiceImpl.getContractInfo发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getContractInfo------------end-->");
			}
		}
		return map;
	}
	@Override
	public List<ContractForm> getListContract() throws BizException{
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getListContract------------begin-->");
		}
		List<ContractForm> list = new ArrayList<ContractForm>();
		try {
			List<ContractEntity> list2 = contractDao.getContractList();
			if(CollectionUtils.isNotEmpty(list2)){
				list = BeanUtils.copyByList(list2, ContractForm.class);
			}
		} catch (Exception e) {
			logger.error("BContractServiceImpl.getListContract发生异常", e);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getListContract------------end-->");
			}
		}
		return list;
	}
	
	public Map<String,String> getAllContractMap(){
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getAllContractMap------------begin-->");
		}
		Map<String, String> response = new HashMap<String, String>();
		try {
			List<ContractForm> listContract = getListContract();
			if(CollectionUtils.isNotEmpty(listContract)){
				response = listContract.stream().collect(Collectors.toMap(o->o.getId()+"", o->o.getContractName()));
			}
			
		} catch (Exception e) {
			logger.error("BContractServiceImpl.getAllContractMap发生异常", e);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getAllContractMap------------end-->");
			}
		}
		return response;
	}

}
