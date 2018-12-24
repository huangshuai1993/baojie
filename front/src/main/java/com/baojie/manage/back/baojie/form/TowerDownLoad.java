package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("楼盘管理downLoad")
public class TowerDownLoad implements Serializable {

	private static final long serialVersionUID = 7542309562103853346L;


	@ApiModelProperty("楼盘名称")
	private String towerName;
	@ApiModelProperty("负责人名称")
	private String functionaryName;
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("合同人数")
	private int peopleCount;
	@ApiModelProperty("实际到岗人数")
	private int virtualCount;
	@ApiModelProperty("进场时间")
	private String approachTime;
	@ApiModelProperty("备注")
	private String memo;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;// 创建时间
	@ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;// 修改时间

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


	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
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
