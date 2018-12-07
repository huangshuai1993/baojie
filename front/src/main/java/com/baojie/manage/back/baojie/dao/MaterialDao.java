package com.baojie.manage.back.baojie.dao;

import java.util.List;

import com.baojie.manage.back.baojie.dao.entity.MaterialEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface MaterialDao extends IEntityDao<MaterialEntity> {

	public PageResults<MaterialEntity> getMaterialList(Integer pageNo, Integer pageSize, Long towerId)
			throws BizException;

	public MaterialEntity addorUpdateMaterial(MaterialEntity materialEntity) throws BizException;

	public void deleteMaterial(MaterialEntity materialEntity) throws BizException;

	public List<MaterialEntity> getMaterialListByMaterialIds(List<Long> ids) throws BizException;

	public List<MaterialEntity> getMaterialListByTowerId(Long id) throws BizException;
}
