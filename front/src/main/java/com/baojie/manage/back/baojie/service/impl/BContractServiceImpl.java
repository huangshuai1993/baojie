package com.baojie.manage.back.baojie.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dao.entity.ContractEntity;
import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.common.enums.ContractStatusEnums;
import com.baojie.manage.back.common.enums.ContractTypeDetailEnums;
import com.baojie.manage.back.common.enums.ContractTypeEnums;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.google.common.collect.Lists;

@Service("bcontractService")
public class BContractServiceImpl extends BaseService implements BContractService {

	@Autowired
	private BContractDao contractDao;

	@Override
	public PageResults<ContractForm> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getAllContract------------begin-->");
		}
		PageResults<ContractForm> page = new PageResults<>();
		try {
			PageResults<ContractEntity> contractPageList = contractDao.getContractList(pageNumber, pageSize,
					contractName, towerName, status);
			if (contractPageList != null) {
				List<ContractEntity> list = contractPageList.getList();
				if (!CollectionUtils.isEmpty(list)) {
					List<ContractForm> list2 = BeanUtils.copyByList(list, ContractForm.class);
					for (ContractForm contractForm : list2) {
						contractForm.setTypeName(ContractTypeEnums.getName(contractForm.getType()));
						contractForm.setDetailTypeName(ContractTypeDetailEnums.getName(contractForm.getDetailType()));
						contractForm.setStatusName(ContractStatusEnums.getName(contractForm.getStatus()));
					}
					page = new PageResults<ContractForm>(list2, pageNumber, pageSize, contractPageList.getTotalCount());
				}else{
					page = new PageResults<ContractForm>(Lists.newArrayList(), pageNumber, pageSize, contractPageList.getTotalCount());
				}
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
			if(contract.getId() != null){
				entity = contractDao.selectByPK(contract.getId());
				BeanUtils.copyPropertiesNotNUll(contract, entity);
				entity.setUpdated(new Date());
				entity = contractDao.update(entity);
			}else{
				entity = new ContractEntity();
				BeanUtils.copyProperties(contract, entity);
				entity = contractDao.insert(entity);
			}
			if(entity!=null){
				result = 1;
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
			ContractEntity contract = contractDao.selectByPK(id);
			if(contract == null){
				map.put(Const.retCode, false);
                map.put(Const.retMsg, "无合同信息");
                return map;
			}
			contractDao.deleteByPK(id);
			map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
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
			ContractEntity contract = contractDao.selectByPK(id);
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
}
