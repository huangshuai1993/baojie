package com.baojie.manage.back.baojie.service;

import com.baojie.manage.back.baojie.dto.ContractDto;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BContractService {

	public PageResults<ContractDto> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException;
}
