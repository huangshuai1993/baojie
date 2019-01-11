package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.entity.ConfigEntity;
import com.baojie.manage.back.baojie.mapper.ConfigEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author huangshuai
 *
 * @date 2019年1月10日
 */
@Repository
public class ConfigDao extends BaseDao<ConfigEntity> {

	@Autowired
	private ConfigEntityMapper configEntityMapper;

	public List<ConfigEntity> getAllConfig(Integer pageNumber, Integer pageSize, String configDesc){
		PageHelper.startPage(pageNumber, pageSize);
		Example example = new Example(ConfigEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(configDesc)) {
			c.andLike("configDesc", "%"+configDesc+"%");
		}
		example.orderBy("updated").desc();
		return configEntityMapper.selectByExample(example);
	}
}
