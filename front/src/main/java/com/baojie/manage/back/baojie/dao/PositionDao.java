package com.baojie.manage.back.baojie.dao;

import java.util.List;

import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface PositionDao extends IEntityDao<PositionEntity> {

	
	public PositionEntity getPositionByName(String name) throws BizException;

	public PageResults<PositionEntity> getPositionList(Integer pageNo, Integer pageSize,Long towerId) throws BizException;

	public PositionEntity addorUpdatePosition(PositionEntity positionEntity) throws BizException;

	public void deletePosition(PositionEntity position) throws BizException;

	public List<PositionEntity> getPositionListByPositionIds(List<Long> ids) throws BizException;
	
	public List<PositionEntity> getPositionListByTowerId(Long id) throws BizException;
}
