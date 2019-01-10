package com.baojie.manage.back.baojie.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.MaterialDao;
import com.baojie.manage.back.baojie.dao.entity.MaterialEntity;
import com.baojie.manage.back.baojie.mapper.MaterialEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class MaterialDao extends BaseDao<MaterialEntity> {

	@Autowired
	private MaterialEntityMapper materialEntityMapper;

	public List<MaterialEntity> getMaterialList(Integer pageNo, Integer pageSize, Long towerId,String beginTime, String endTime){
		PageHelper.startPage(pageNo, pageSize);
		Example example = new Example(MaterialEntity.class);
		Example.Criteria c = example.createCriteria();
		if (towerId !=null) {
			c.andEqualTo("towerId", towerId);
		}
		if(StringUtils.isNotBlank(beginTime)){
			c.andGreaterThanOrEqualTo("purchaseTime", beginTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			c.andLessThanOrEqualTo("purchaseTime", endTime);
		}
		example.orderBy("updated").desc();
		return materialEntityMapper.selectByExample(example);
	}
			

}
