package com.baojie.manage.back.baojie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.baojie.dao.BContractDao;
import com.baojie.manage.back.baojie.dto.ContractDto;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.common.enums.ExampleExCode;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;

@Service("bcontractService")
public class BContractServiceImpl extends BaseService implements BContractService {

	@Autowired
	private BContractDao contractDao;

	@Override
	public PageResults<ContractDto> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("--------------BContractServiceImpl.getAllContract------------begin-->");
		}
		PageResults<ContractDto> page = new PageResults<>();
		try {

		} catch (Exception e) {
			logger.error("BContractServiceImpl.getAllContract发生异常", e);
			throw new BizException(ExampleExCode.EXAMPLE_NOT_FOUND);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("--------------BContractServiceImpl.getAllContract------------end-->");
			}
		}
		return null;
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
