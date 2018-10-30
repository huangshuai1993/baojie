package com.baojie.manage.back.organize.dao;

import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.dao.entity.StoreEntity;
import com.baojie.manage.base.exception.BizException;

public interface StoreDao extends IEntityDao<StoreEntity> {

	public PageResults<StoreEntity> getStoreList(String province, String name, Integer pageNumber, Integer pageSize)
			throws BizException;

	public StoreEntity getStoreByName(String name) throws BizException;
}
