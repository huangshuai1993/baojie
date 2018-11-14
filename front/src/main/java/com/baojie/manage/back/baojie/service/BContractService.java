package com.baojie.manage.back.baojie.service;

import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BContractService {

	public PageResults<ContractForm> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException;
}
