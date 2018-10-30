package com.baojie.manage.back.sys.service.convertor;

import org.springframework.stereotype.Component;

import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.dto.Employee_personaDto;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

@Component
public class Employee_personaConvertor extends AbstractConvertor<Employee_personaDto, Employee_personaEntity> {

	@Override
	public Employee_personaDto entity2Dto(Employee_personaEntity entity) {
		if (entity == null) {
			return null;
		}
		Employee_personaDto dto = new Employee_personaDto();
		dto.setEmpId(entity.getEmpId());
		dto.setEmployeeId(entity.getEmployeeId());
		dto.setPersonaId(entity.getPersonaId());
		return dto;
	}

	@Override
	public Employee_personaEntity dto2Entity(Employee_personaDto dto) {
		if (dto == null) {
			return null;
		}
		Employee_personaEntity en = new Employee_personaEntity();
		en.setEmpId(dto.getEmpId());
		en.setEmployeeId(dto.getEmployeeId());
		en.setPersonaId(dto.getPersonaId());
		return en;
	}

}
