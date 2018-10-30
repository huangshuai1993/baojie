package com.baojie.manage.back.sys.service.convertor;

import org.springframework.stereotype.Component;

import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.back.sys.dto.PowerDto;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

@Component
public class PowerConvertor extends AbstractConvertor<PowerDto, PowerEntity> {

	@Override
	public PowerDto entity2Dto(PowerEntity entity) {
		if (entity == null) {
			return null;
		}
		PowerDto dto = new PowerDto();
		dto.setMenuName(entity.getMenuName());
		dto.setParentId(entity.getParentId());
		dto.setPowerId(entity.getPowerId());
		dto.setPowerName(entity.getPowerName());
		dto.setPowerUrl(entity.getPowerUrl());
		return dto;
	}

	@Override
	public PowerEntity dto2Entity(PowerDto dto) {
		if (dto == null) {
			return null;
		}
		PowerEntity en = new PowerEntity();
		en.setMenuName(dto.getMenuName());
		en.setParentId(dto.getParentId());
		en.setPowerId(dto.getPowerId());
		en.setPowerName(dto.getPowerName());
		en.setPowerUrl(dto.getPowerUrl());
		return en;
	}

}
