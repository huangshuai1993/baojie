package com.baojie.manage.base.common.service;

import java.util.Date;

/**
 * 每个实体类继承该父类
 */
public abstract class BaseDO {

	/** 备注 */
	private String memo;

	/** 创建时间 */
	private Date created;

	/** 修改时间 */
	private Date updated;

	/** 数据标志，0 无效 1 有效 */
	private Integer dataFlag;

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
