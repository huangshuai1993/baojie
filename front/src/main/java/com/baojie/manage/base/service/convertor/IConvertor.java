package com.baojie.manage.base.service.convertor;

import java.util.List;

import com.baojie.manage.base.dao.IEntity;
import com.baojie.manage.base.service.IDto;


public interface IConvertor<D extends IDto, E extends IEntity> {

	public D entity2Dto(E entity);
	
	public E dto2Entity(D dto);
	
	public List<D> entity2DtoList(List<E> entities);
	
	public List<E> dto2EntityList(List<D> dtos);
	
	public E vo2Entity(Object vo);
	
}
