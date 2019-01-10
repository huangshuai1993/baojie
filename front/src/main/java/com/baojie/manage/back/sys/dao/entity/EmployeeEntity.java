package com.baojie.manage.back.sys.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

@Table(name = "sys_employee")
@NameStyle
public class EmployeeEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = 2353668247642070919L;

	@Id
	@Column(name = "employeeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;// 员工id

	@Column(name = "custNo", length = 64)
	private String custNo;// 员工号

	@Column(name = "userName", length = 50)
	private String userName;// 员工登录账号

	@Column(name = "passWord", length = 50)
	private String passWord;// 员工登录密码

	@Column(name = "realName", length = 20)
	private String realName;// 员工姓名

	@Column(name = "qq", length = 20)
	private String qq;// 员工QQ

	@Column(name = "telePhone", length = 20)
	private String telePone;// 员工办公电话

	@Column(name = "position", length = 20)
	private String position;// 员工职位

	@Column(name = "sex", length = 4)
	private String sex;// 员工性别

	@Column(name = "email", length = 20)
	private String email;// 员工邮箱

	@Column(name = "phone", length = 20)
	private String phone;// 员工手机号

	@Column(name = "employIDCardNum", length = 30)
	private String employIDCardNum;// 员工身份证

	@Column(name = "empRegTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date empRegTime;// 注册时间

	@Column(name = "lastLoginTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;// 最后登录时间

	@Column(name = "empStatus", length = 5)
	private String empStatus;// 员工账号状态

	@Column(name = "employeeType", length = 5)
	private String employeeType;// 主要用于区别admin

	public EmployeeEntity() {

	}

	public EmployeeEntity(EmployeeDto dto) {
		this.employeeId = dto.getEmployeeId();
		this.custNo = dto.getCustNo();
		this.userName = dto.getUserName();
		this.passWord = dto.getPassWord();
		this.realName = dto.getRealName();
		this.qq = dto.getQq();
		this.telePone = dto.getTelePhone();
		this.position = dto.getPosition();
		this.sex = dto.getSex();
		this.email = dto.getEmail();
		this.phone = dto.getPhone();
		this.employIDCardNum = dto.getEmployIDCardNum();
		this.empRegTime = dto.getEmpRegTime();
		this.lastLoginTime = dto.getLastLoginTime();
		this.empStatus = dto.getEmpStatus();
		this.employeeType = dto.getEmployeeType();
	}


	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return passWord;
	}

	public void setPassword(String password) {
		this.passWord = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getTelePone() {
		return telePone;
	}

	public void setTelePone(String telePone) {
		this.telePone = telePone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmployIDCardNum() {
		return employIDCardNum;
	}

	public void setEmployIDCardNum(String employIDCardNum) {
		this.employIDCardNum = employIDCardNum;
	}

	public Date getEmpRegTime() {
		return empRegTime;
	}

	public void setEmpRegTime(Date empRegTime) {
		this.empRegTime = empRegTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

}
