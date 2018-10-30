package com.baojie.manage.back.sys.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.baojie.manage.base.service.IDto;

public class PersonaDto implements IDto {

	private static final long serialVersionUID = -4172836835141151875L;

	private Long personaId; // 角色id

	private String personaName; // 角色名

	private String personaDesc;// 角色描述

	private String personaEnable;// 角色是否启用

	public Long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}

	public String getPersonaName() {
		return personaName;
	}

	public void setPersonaName(String personaName) {
		this.personaName = personaName;
	}

	public String getPersonaDesc() {
		return personaDesc;
	}

	public void setPersonaDesc(String personaDesc) {
		this.personaDesc = personaDesc;
	}

	public String getPersonaEnable() {
		return personaEnable;
	}

	public void setPersonaEnable(String personaEnable) {
		this.personaEnable = personaEnable;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}
}
