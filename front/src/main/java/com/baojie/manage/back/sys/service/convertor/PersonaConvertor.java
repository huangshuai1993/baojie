package com.baojie.manage.back.sys.service.convertor;

import org.springframework.stereotype.Component;

import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.back.sys.dto.PersonaDto;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

@Component
public class PersonaConvertor extends AbstractConvertor<PersonaDto, PersonaEntity> {

	@Override
	public PersonaDto entity2Dto(PersonaEntity entity) {

		if (entity == null) {
			return null;
		}
		PersonaDto dto = new PersonaDto();
		dto.setPersonaDesc(entity.getPersonaDesc());
		dto.setPersonaEnable(entity.getPersonaEnable());
		dto.setPersonaId(entity.getPersonaId());
		dto.setPersonaName(entity.getPersonaName());
		return dto;
	}

	@Override
	public PersonaEntity dto2Entity(PersonaDto dto) {

		if (dto == null) {
			return null;
		}
		PersonaEntity ne = new PersonaEntity();
		ne.setPersonaDesc(dto.getPersonaDesc());
		ne.setPersonaEnable(dto.getPersonaEnable());
		ne.setPersonaId(dto.getPersonaId());
		ne.setPersonaName(dto.getPersonaName());
		return ne;
	}

}
