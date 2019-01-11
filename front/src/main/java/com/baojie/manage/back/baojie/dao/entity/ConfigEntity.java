package com.baojie.manage.back.baojie.dao.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

/**
 * 配置信息表(数据字典-状态)
 * 
 * @author huangshuai
 *
 * @date 2019年1月10日
 */
@Table(name = "opt_config")
@NameStyle
public class ConfigEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = -6899339798518316459L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id

	private String configuration;// 配置项
	private String configDesc;// 配置项描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	
}
