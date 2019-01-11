package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.mapper.ConfigDetailEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

@Repository
public class ConfigDetailDao extends BaseDao<ConfigDetailEntity> {

	@Autowired
	private ConfigDetailEntityMapper configDetailEntityMapper;

	public List<ConfigDetailEntity>  getAllConfigDetail(Integer pageNumber, Integer pageSize, String configuration,String configDetailDesc){
		PageHelper.startPage(pageNumber, pageSize);
		Example example = new Example(ConfigDetailEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(configuration)) {
			c.andEqualTo("configuration",configuration);
		}
		if (StringUtils.isNotBlank(configDetailDesc)) {
			c.andLike("configDetailDesc","%"+configDetailDesc+"%");
		}
		example.orderBy("updated").desc();
		return configDetailEntityMapper.selectByExample(example);
	}
	/**
	 * 查询配置项明细列表
	 * @param configuration
	 * @return
	 */
	public List<ConfigDetailEntity> queryConfigDetailByConfig(String configuration){
		Example example = new Example(ConfigDetailEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(configuration)) {
			c.andEqualTo("configuration",configuration);
		}
		example.orderBy("updated").desc();
		return configDetailEntityMapper.selectByExample(example);
	}
	/**
	 * 查询配置项多少条
	 * @param configuration
	 * @return
	 */
	public int queryConfigDetailByConfigCount(String configuration){
		Example example = new Example(ConfigDetailEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(configuration)) {
			c.andEqualTo("configuration",configuration);
		}
		return configDetailEntityMapper.selectCountByExample(example);
	}
}
