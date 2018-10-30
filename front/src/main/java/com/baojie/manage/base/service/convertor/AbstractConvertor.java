package com.baojie.manage.base.service.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baojie.manage.base.dao.IEntity;
import com.baojie.manage.base.service.IDto;
import com.google.common.collect.Lists;

public abstract class AbstractConvertor<D extends IDto, E extends IEntity> implements IConvertor<D, E> {

	@Override
	public D entity2Dto(E entity) {
		return null;
	}

	@Override
	public E dto2Entity(D dto) {
		return null;
	}

	@Override
	public List<D> entity2DtoList(List<E> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return Lists.newArrayList();
		}
		List<D> dtoList = new ArrayList<D>();
		for (E e : entities) {
			if (e == null) {
				continue;
			}
			dtoList.add(entity2Dto(e));
		}
		return dtoList;
	}

	@Override
	public List<E> dto2EntityList(List<D> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return Lists.newArrayList();
		}
		List<E> entityList = new ArrayList<E>();
		for (D dto : dtos) {
			if (dto == null) {
				continue;
			}
			entityList.add(dto2Entity(dto));
		}
		return entityList;
	}

	@Override
	public E vo2Entity(Object vo) {
		return null;
	}

}
