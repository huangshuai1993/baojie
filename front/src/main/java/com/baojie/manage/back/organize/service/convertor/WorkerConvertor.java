package com.baojie.manage.back.organize.service.convertor;

import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.base.dao.entity.WorkerEntity;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

public class WorkerConvertor extends AbstractConvertor<WorkerDto, WorkerEntity> {
	@Override
	public WorkerDto entity2Dto(WorkerEntity entity) {
		if (entity == null) {
			return null;
		}
		WorkerDto dto = new WorkerDto();
		dto.setId(entity.getId());
		dto.setStoreId(entity.getStoreId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setGender(entity.getGender());
		dto.setIdcardNo(entity.getIdcardNo());
		dto.setBirthday(entity.getBirthday());
		dto.setMobile(entity.getMobile());
		dto.setEmpNo(entity.getEmpNo());
		dto.setEmail(entity.getEmail());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	@Override
	public WorkerEntity dto2Entity(WorkerDto dto) {
		if (dto == null) {
			return null;
		}
		WorkerEntity entity = new WorkerEntity();
		entity.setId(dto.getId());
		entity.setStoreId(dto.getStoreId());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setGender(dto.getGender());
		entity.setIdcardNo(dto.getIdcardNo());
		entity.setBirthday(dto.getBirthday());
		entity.setMobile(dto.getMobile());
		entity.setEmpNo(dto.getEmpNo());
		entity.setEmail(dto.getEmail());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		entity.setStatus(dto.getStatus());
		return entity;
	}

}
