package com.baojie.manage.back.task.dao;

import java.util.List;

import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.dao.entity.PhotoEntity;
import com.baojie.manage.base.exception.BizException;

public interface BPhotoDao extends IEntityDao<PhotoEntity> {

	/**
	 * 根据taskId获得图片信息
	 * 
	 * @param taskId
	 * @return
	 * @throws BizException
	 */
	public List<PhotoEntity> getPhotoListByTaskId(Long taskId) throws BizException;
}
