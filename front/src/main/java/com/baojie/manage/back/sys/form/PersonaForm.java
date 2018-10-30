package com.baojie.manage.back.sys.form;

import java.io.Serializable;

import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.back.sys.dto.PersonaDto;


public class PersonaForm implements Serializable{
	private static final long serialVersionUID = -956140062401576842L;
	private Long personaId; // 角色id
    private String personaName; // 角色名
    private String personaDesc;// 角色描述
    private String personaEnable;// 角色是否启用
    public PersonaForm() {
	}

	public PersonaForm(PersonaDto dto) {
		this.personaId = dto.getPersonaId();
		this.personaName = dto.getPersonaName();
		this.personaDesc = dto.getPersonaDesc();
		this.personaEnable = dto.getPersonaEnable();
	}
	public PersonaForm(PersonaEntity en) {
		this.personaId = en.getPersonaId();
		this.personaName = en.getPersonaName();
		this.personaDesc = en.getPersonaDesc();
		this.personaEnable = en.getPersonaEnable();
	}
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

}
