package com.baojie.manage.back.organize.dao;


import java.util.List;

import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.dao.entity.WorkerEntity;
import com.baojie.manage.base.exception.BizException;
public interface WorkerDao extends IEntityDao<WorkerEntity> {
	
	public PageResults<WorkerEntity> getWorkerList(Long storeId,String name,String mobile,Integer status,Integer pageNumber, Integer pageSize) throws BizException;
	
	public WorkerEntity getWorkerByIdCard(String idcardNo) throws BizException;
	public WorkerEntity getWorkerByMobile(String mobile) throws BizException;
	public List<WorkerEntity> getWorkerByStoreId(Long storeId) throws BizException;
}
