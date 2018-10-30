package com.baojie.manage.back.sys.service.convertor;

import org.springframework.stereotype.Component;

import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

@Component
public class EmployeeConvertor extends AbstractConvertor<EmployeeDto, EmployeeEntity> {

	@Override
	public EmployeeDto entity2Dto(EmployeeEntity entity) {
		if (entity == null) {
			return null;
		}
		EmployeeDto dto = new EmployeeDto();
		dto.setEmployeeId(entity.getEmployeeId());
		dto.setCustNo(entity.getCustNo());
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setRealName(entity.getRealName());
		dto.setQq(entity.getQq());
		dto.setTelephone(entity.getTelephone());
		dto.setPosition(entity.getPosition());
		dto.setSex(entity.getSex());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setEmployIDCardNum(entity.getEmployIDCardNum());
		dto.setEmpRegTime(entity.getEmpRegTime());
		dto.setLastLoginTime(entity.getLastLoginTime());
		dto.setEmpStatus(entity.getEmpStatus());
		dto.setEmployeeType(entity.getEmployeeType());
		return dto;
	}

	@Override
	public EmployeeEntity dto2Entity(EmployeeDto dto) {
		if (dto == null) {
			return null;
		}
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmployeeId(dto.getEmployeeId());
		entity.setCustNo(dto.getCustNo());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setRealName(dto.getRealName());
		entity.setQq(dto.getQq());
		entity.setTelephone(dto.getTelephone());
		entity.setPosition(dto.getPosition());
		entity.setSex(dto.getSex());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setEmployIDCardNum(dto.getEmployIDCardNum());
		entity.setEmpRegTime(dto.getEmpRegTime());
		entity.setLastLoginTime(dto.getLastLoginTime());
		entity.setEmpStatus(dto.getEmpStatus());
		entity.setEmployeeType(dto.getEmployeeType());
		return entity;
	}

}
