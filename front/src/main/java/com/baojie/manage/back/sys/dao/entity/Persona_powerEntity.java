package com.baojie.manage.back.sys.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

@Table(name = "sys_persona_power")
@NameStyle
public class Persona_powerEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = -6168897443486562613L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "personaId", length = 11)
	private Long personaId;// 角色id

	@Column(name = "powerId", length = 11)
	private Long powerId;// 权限id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}

	public Long getPowerId() {
		return powerId;
	}

	public void setPowerId(Long powerId) {
		this.powerId = powerId;
	}

}
