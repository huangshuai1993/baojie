package com.baojie.manage.back.baojie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.entity.ConfigDetailEntity;
import com.baojie.manage.back.baojie.mapper.ConfigDetailEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;

/**
 * @author xuemengqi
 * @date 2019年01月10日
 */
@Repository
public class ConfigDetailDao extends BaseDao<ConfigDetailEntity> {

	@SuppressWarnings("unused")
	@Autowired
	private ConfigDetailEntityMapper configDetailEntityMapper;

}
