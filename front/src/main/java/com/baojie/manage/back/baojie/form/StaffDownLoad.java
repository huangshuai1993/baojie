package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("员工管理downLoad")
public class StaffDownLoad implements Serializable {

	private static final long serialVersionUID = -8962790558205609276L;
	@ApiModelProperty("员工姓名")
	private String name;
	@ApiModelProperty("身份证号")
	private String idCard;
	@ApiModelProperty("年龄")
	private int age;
	@ApiModelProperty("性别")
	private String genderName;// 1男，0女
	@ApiModelProperty("出生日期")
	private String birthday;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("所属楼盘")
	private String towerName;
	@ApiModelProperty("职位")
	private String positionName;
	@ApiModelProperty("工作状态")
	private String statusName;// 0在职，1离职
	@ApiModelProperty("备注")
	private String memo;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;// 创建时间
	@ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated;// 修改时间
	

	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

}
