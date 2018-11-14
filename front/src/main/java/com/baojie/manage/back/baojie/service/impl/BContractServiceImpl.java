package com.baojie.manage.back.baojie.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dao.Entity.ContractEntity;
import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

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
					page = new PageResults<ContractForm>(list2, pageNumber, pageSize, contractPageList.getTotalCount());
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
}
