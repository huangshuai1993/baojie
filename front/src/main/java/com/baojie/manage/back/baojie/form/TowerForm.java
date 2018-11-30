package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TowerForm implements Serializable {

	private static final long serialVersionUID = -1104866958897704768L;

	private Long towerId;// id

	private String towerName;
	private Long functionaryId;// 负责人id

	private String functionaryName;
	private String address;
	private Long contractId;
	private int peopleCount;
	private int virtualCount;

	private String approachTime;

	private String memo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;// 修改时间

	/**
	 * 状态 默认1可用
	 */
	@Column
	private int dataFlag;

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

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

	public Long getFunctionaryId() {
		return functionaryId;
	}

	public void setFunctionaryId(Long functionaryId) {
		this.functionaryId = functionaryId;
	}

	public String getFunctionaryName() {
		return functionaryName;
	}

	public void setFunctionaryName(String functionaryName) {
		this.functionaryName = functionaryName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public int getVirtualCount() {
		return virtualCount;
	}

	public void setVirtualCount(int virtualCount) {
		this.virtualCount = virtualCount;
	}

	public String getApproachTime() {
		return approachTime;
	}

	public void setApproachTime(String approachTime) {
		this.approachTime = approachTime;
	}

}
