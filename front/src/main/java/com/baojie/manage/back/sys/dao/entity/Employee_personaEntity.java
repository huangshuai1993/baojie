package com.baojie.manage.back.sys.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

@Table(name = "sys_employee_persona")
@NameStyle
public class Employee_personaEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = -4351364743781036989L;
	@Id
	@Column(name = "empId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId; // id

	@Column(name = "employeeId", length = 11)
	private Long employeeId; // 员工id

	@Column(name = "personaId", length = 11)
	private Long personaId; // 角色id

	public Employee_personaEntity() {

	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}

}
