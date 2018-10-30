package com.baojie.manage.back.sys.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.baojie.manage.base.service.IDto;

public class Employee_personaDto implements IDto {

	private static final long serialVersionUID = -4351364743781036989L;
	private Long empId; // id

	private Long employeeId; // 员工id

	private Long personaId; // 角色id

	public Employee_personaDto() {

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

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
