package com.baojie.manage.back.sys.form;

import java.io.Serializable;
import java.util.Date;

import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;


public class EmployeeForm implements Serializable  {

    private static final long serialVersionUID = 1L;
    private Long employeeId;// 员工id
    private String custNo;// 员工号
    private String userName;// 员工登录账号
    private String passWord;// 员工登录密码
    private String realName;// 员工姓名
    private String qq;// 员工QQ
    private String telePhone;// 员工办公电话
    private String position;// 员工职位
    private String sex;// 员工性别
    private String email;// 员工邮箱
    private String phone;// 员工手机号
    private String employIDCardNum;// 员工身份证
    private Date empRegTime;// 注册时间
    private Date lastLoginTime;// 最后登录时间
    private String empStatus;// 员工账号状态
    private String employeeType;// 主要用于区别admin
    //員工角色名称
    private String persoaName="";
    
    public String getPersoaName() {
		return persoaName;
	}

	public void setPersoaName(String persoaName) {
		this.persoaName = persoaName;
	}

    public EmployeeForm(){
        
    }
    public EmployeeForm(EmployeeDto dto) {
          this.employeeId = dto.getEmployeeId();
          this.custNo = dto.getCustNo();
          this.userName = dto.getUserName();
          this.passWord = dto.getPassWord();
          this.realName = dto.getRealName();
          this.qq = dto.getQq();
          this.telePhone = dto.getTelePhone();
          this.position = dto.getPosition();
          this.sex = dto.getSex();
          this.email = dto.getEmail();
          this.phone = dto.getPhone();
          this.employIDCardNum = dto.getEmployIDCardNum();
          this.empRegTime = dto.getEmpRegTime();
          this.lastLoginTime = dto.getLastLoginTime();
          this.empStatus = dto.getEmpStatus();
          this.employeeType = dto.getEmployeeType();
          this.persoaName=dto.getPersoaName();
      }
    public EmployeeForm(EmployeeEntity entity) {
        this.employeeId = entity.getEmployeeId();
        this.custNo = entity.getCustNo();
        this.userName = entity.getUsername();
        this.passWord = entity.getPassword();
        this.realName = entity.getRealName();
        this.qq = entity.getQq();
        this.telePhone = entity.getTelePone();
        this.position = entity.getPosition();
        this.sex = entity.getSex();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.employIDCardNum = entity.getEmployIDCardNum();
        this.empRegTime = entity.getEmpRegTime();
        this.lastLoginTime = entity.getLastLoginTime();
        this.empStatus = entity.getEmpStatus();
        this.employeeType = entity.getEmployeeType();
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

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
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
        return telePhone;
    }

    public void setTelephone(String telephone) {
        this.telePhone = telephone;
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
   
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }


    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof EmployeeForm) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.custNo.hashCode() + 29 * 100;
    }
}
