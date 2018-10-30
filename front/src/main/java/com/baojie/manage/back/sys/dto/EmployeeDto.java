package com.baojie.manage.back.sys.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baojie.manage.base.common.util.CustomDateTimeDeserializer;
import com.baojie.manage.base.common.util.CustomDateTimeSerializer;
import com.baojie.manage.base.service.IDto;
public class EmployeeDto implements Serializable,IDto {

	private static final long serialVersionUID = -2391616788366813395L;

	private Long employeeId;// 员工id

    private String custNo;// 员工号
    private String username;// 员工登录账号
    private String password;// 员工登录密码
    private String realName;// 员工姓名
    private String qq;// 员工QQ
    private String telephone;// 员工办公电话
    private String position;// 员工职位
    private String sex;// 员工性别
    private String email;// 员工邮箱
    private String phone;// 员工手机号
    private String employIDCardNum;// 员工身份证
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonDeserialize(using=CustomDateTimeDeserializer.class)
    private Date empRegTime;// 注册时间
    @JsonSerialize(using=CustomDateTimeSerializer.class)
	@JsonDeserialize(using=CustomDateTimeDeserializer.class)
    private Date lastLoginTime;// 最后登录时间
    private String empStatus;// 员工账号状态
    private String employeeType;// 主要用于区别admin
    private Long storeId;//门店id
    
    //员工角色名称
    private String persoaName;
    
    public String getPersoaName() {
		return persoaName;
	}

	public void setPersoaName(String persoaName) {
		this.persoaName = persoaName;
	}

	public EmployeeDto() {

    }
    
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}
}
