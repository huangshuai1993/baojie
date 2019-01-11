package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.util.Date;

/**
 * 配置信息明细表
 * 
 * @author huangshuai
 *
 * @date 2019年1月10日
 */
public class ConfigDetailForm implements Serializable {

	private static final long serialVersionUID = 4332833676891873331L;


	private Long id;// id
	
	private String configuration;//配置项
	private Integer configValue;//配置明细状态
	private String configDetailDesc;//配置明细描述
	
	/** 备注 */
	private String memo;

	/** 创建时间 */
	private Date created;

	/** 修改时间 */
	private Date updated;

	/** 数据标志，0 无效 1 有效 */
	private Integer dataFlag;
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
	
	public Integer getConfigValue() {
		return configValue;
	}
	public void setConfigValue(Integer configValue) {
		this.configValue = configValue;
	}
	
	public String getConfigDetailDesc() {
		return configDetailDesc;
	}
	public void setConfigDetailDesc(String configDetailDesc) {
		this.configDetailDesc = configDetailDesc;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(Integer dataFlag) {
		this.dataFlag = dataFlag;
	}
	
	
}