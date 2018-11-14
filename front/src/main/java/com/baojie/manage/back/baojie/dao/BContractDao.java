package com.baojie.manage.back.baojie.dao;

import com.baojie.manage.back.baojie.dao.Entity.ContractEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface BContractDao extends IEntityDao<ContractEntity> {
	
	public PageResults<ContractEntity> getContractList(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException;


}
