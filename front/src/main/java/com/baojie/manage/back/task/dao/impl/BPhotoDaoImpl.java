package com.baojie.manage.back.task.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.task.dao.BPhotoDao;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.dao.entity.PhotoEntity;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

@Repository("com.crdloo.loan.back.task.dao.impl.PhotoDaoImpl")
public class BPhotoDaoImpl extends AbstractHibernateEntityDao<PhotoEntity> implements BPhotoDao {

	@Override
	public List<PhotoEntity> getPhotoListByTaskId(Long taskId) throws BizException {
		String hql = "from PhotoEntity p where  p.taskId=:taskId";
		Map<String, Object> params = Maps.newHashMap();
		params.put("taskId", taskId);
		List<PhotoEntity> PhotoList = selectList(hql, params);
		return PhotoList;
	}

}
