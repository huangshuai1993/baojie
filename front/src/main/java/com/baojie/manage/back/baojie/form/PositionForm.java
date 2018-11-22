package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 职位名称
 * 
 * @author huangshuai
 *
 * @date 2018年11月22日
 */
public class PositionForm implements Serializable {
	private static final long serialVersionUID = -8235995412804228404L;
	private Long positionId;// id

	private String positionName; // 职位名称,

	private Long towerId; // 所属楼盘,

	private String towerName; // 楼盘名称,

	private BigDecimal basePay; // 基本工资,

	private BigDecimal allowance; // 岗位津贴,

	private String memo;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;// 创建时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;// 修改时间

	/**
	 * 状态 默认1可用
	 */

	private int dataFlag = 1;

	public Long getTowerId() {
		return towerId;
	}

	public void setTowerId(Long towerId) {
		this.towerId = towerId;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
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

	public int getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public BigDecimal getBasePay() {
		return basePay;
	}

	public void setBasePay(BigDecimal basePay) {
		this.basePay = basePay;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
