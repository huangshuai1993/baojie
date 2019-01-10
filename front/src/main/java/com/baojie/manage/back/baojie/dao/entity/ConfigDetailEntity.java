package com.baojie.manage.back.baojie.dao.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

/**
 * 配置信息明细表
 * 
 * @author huangshuai
 *
 * @date 2019年1月10日
 */
@Table(name = "opt_config_detail")
@NameStyle
public class ConfigDetailEntity extends BaseDO implements Serializable {
	private static final long serialVersionUID = 6930874665994967859L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id
	
	private String configuration;//配置项
	private Integer status;//配置明细状态
	private String desc;//配置明细描述
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}