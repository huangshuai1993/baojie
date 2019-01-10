package com.baojie.manage.back.baojie.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

@Table(name = "opt_tower")
@NameStyle
public class TowerEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = -3747404580948928227L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long towerId;// id

	@Column
	private String towerName;
	@Column
	private Long functionaryId;// 负责人id

	@Column
	private String functionaryName;
	@Column
	private String address;
	@Column
	private Long contractId;
	@Column
	private Integer peopleCount;
	@Column
	private Integer virtualCount;

	@Column
	private String approachTime;

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

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Integer getVirtualCount() {
		return virtualCount;
	}

	public void setVirtualCount(Integer virtualCount) {
		this.virtualCount = virtualCount;
	}

	public String getApproachTime() {
		return approachTime;
	}

	public void setApproachTime(String approachTime) {
		this.approachTime = approachTime;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
