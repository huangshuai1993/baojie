package com.baojie.manage.back.organize.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.base.common.util.CustomDateTimeDeserializer;
import com.baojie.manage.base.common.util.CustomDateTimeSerializer;
import com.baojie.manage.base.common.util.DateTimeUtil;
import com.baojie.manage.base.dao.entity.WorkerEntity;

public class WorkerForm implements Serializable {

	private static final long serialVersionUID = 4892553441635578583L;

	private Long id;// id

	private Long storeId;// 门店id
	private String name;// 姓名

	private String password;// 密码

	private int gender;// 性别(0=女,1=男)'

	private String idcardNo;// 身份证号
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date birthday;// 出生日期

	private String birthdayStr;// 出生日期字符串

	private String mobile;// 手机号

	private String empNo;// 工号

	private String email;// 邮箱

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date created;// 创建时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date updated;// 修改时间

	/**
	 * 状态 默认1可用
	 */
	private int status;
	private Long taskCount = 0l;

	public WorkerForm() {
	}

	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public WorkerForm(WorkerDto dto) {
		this.id = dto.getId();
		this.storeId = dto.getStoreId();
		this.name = dto.getName();
		this.password = dto.getPassword();
		this.gender = dto.getGender();
		this.idcardNo = dto.getIdcardNo();
		this.birthday = dto.getBirthday();
		this.mobile = dto.getMobile();
		this.empNo = dto.getEmpNo();
		this.email = dto.getEmail();
		this.created = dto.getCreated();
		this.updated = dto.getUpdated();
		this.status = dto.getStatus();
		this.taskCount = (dto.getTaskCount() == null ? 0 : dto.getTaskCount());
	}

	public WorkerForm(WorkerEntity entity) {
		this.id = entity.getId();
		this.storeId = entity.getStoreId();
		this.name = entity.getName();
		this.gender = entity.getGender();
		this.idcardNo = entity.getIdcardNo();
		this.birthday = entity.getBirthday();
		this.birthdayStr = DateTimeUtil.DateToStr(entity.getBirthday());
		this.mobile = entity.getMobile();
		this.empNo = entity.getEmpNo();
		this.email = entity.getEmail();
		this.created = entity.getCreated();
		this.updated = entity.getUpdated();
		this.status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
