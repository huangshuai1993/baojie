package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BContractService {

	public PageResults<ContractForm> getAllContract(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException;
	
	public Integer addContract(ContractForm contract) throws BizException;
	
	public Map<String, Object> deleteContract(Long id)throws BizException;
	
	public Map<String, Object> getContractInfo(Long id)throws BizException;
}
