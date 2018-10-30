package com.baojie.manage.back.organize.service.convertor;

import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.base.dao.entity.StoreEntity;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

public class StoreConvertor extends AbstractConvertor<StoreDto, StoreEntity> {
	@Override
	public StoreDto entity2Dto(StoreEntity entity) {
		if (entity == null) {
			return null;
		}
		StoreDto dto = new StoreDto();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setName(entity.getName());
		dto.setProvinceCode(entity.getProvinceCode());
		dto.setStatus(entity.getStatus());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

	@Override
	public StoreEntity dto2Entity(StoreDto dto) {
		if (dto == null) {
			return null;
		}
		StoreEntity entity = new StoreEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setProvinceCode(dto.getProvinceCode());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		entity.setStatus(dto.getStatus());

		return entity;
	}

}
