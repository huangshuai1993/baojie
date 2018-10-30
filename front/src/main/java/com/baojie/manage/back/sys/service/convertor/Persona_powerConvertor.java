package com.baojie.manage.back.sys.service.convertor;

import org.springframework.stereotype.Component;

import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.back.sys.dto.Persona_powerDto;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

@Component
public class Persona_powerConvertor extends AbstractConvertor<Persona_powerDto, Persona_powerEntity> {

	@Override
	public Persona_powerDto entity2Dto(Persona_powerEntity entity) {
		if (entity == null) {
			return null;
		}
		Persona_powerDto dto = new Persona_powerDto();
		dto.setId(entity.getId());
		dto.setPersonaId(entity.getPersonaId());
		dto.setPowerId(entity.getPowerId());
		return dto;
	}

	@Override
	public Persona_powerEntity dto2Entity(Persona_powerDto dto) {
		if (dto == null) {
			return null;
		}
		Persona_powerEntity en = new Persona_powerEntity();
		en.setId(dto.getId());
		en.setPersonaId(dto.getPersonaId());
		en.setPowerId(dto.getPowerId());
		return en;
	}

}
