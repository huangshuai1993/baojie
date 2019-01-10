package com.baojie.manage.back.baojie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.entity.ConfigEntity;
import com.baojie.manage.back.baojie.mapper.ConfigEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;

/**
 * 
 * @author huangshuai
 *
 * @date 2019年1月10日
 */
@Repository
public class ConfigDao extends BaseDao<ConfigEntity> {

	@SuppressWarnings("unused")
	@Autowired
	private ConfigEntityMapper configEntityMapper;


}
