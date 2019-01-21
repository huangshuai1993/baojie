package com.baojie.manage.back.baojie.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.entity.ContractEntity;
import com.baojie.manage.back.baojie.mapper.ContractEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class ContractDao extends BaseDao<ContractEntity>{

	@Autowired
	private ContractEntityMapper contractEntityMapper;

	public List<ContractEntity> getContractList(Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status){
		PageHelper.startPage(pageNumber, pageSize);
		Example example = new Example(ContractEntity.class);
	    Example.Criteria c = example.createCriteria();
	    if(StringUtils.isNotBlank(contractName)){
	    	c.andEqualTo("contractName",contractName);
	    }
	    if(StringUtils.isNotBlank(towerName)){
	    	c.andEqualTo("towerName",towerName);
	    }
	    if(status != null){
	    	c.andEqualTo("status",status);
	    }
	    example.orderBy("updated").desc();
	    return contractEntityMapper.selectByExample(example);
	}
	
	public List<ContractEntity> getContractList(){
		Example example = new Example(ContractEntity.class);
	    Example.Criteria c = example.createCriteria();
	    example.orderBy("updated").desc();
	    return contractEntityMapper.selectByExample(example);
	}
}
