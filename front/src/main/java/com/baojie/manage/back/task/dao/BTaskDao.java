package com.baojie.manage.back.task.dao;

import java.util.List;
import java.util.Map;

import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.dao.entity.TaskEntity;
import com.baojie.manage.base.exception.BizException;

public interface BTaskDao extends IEntityDao<TaskEntity> {

	public PageResults<TaskEntity> getTaskList(String name, String mobile, Long workId, Integer taskStatus,
			Long SearchStoreId, Integer pageNumber, Integer pageSize) throws BizException;

	public PageResults<TaskEntity> getTaskListByStatus(String name, String mobile, Long workId, Integer taskStatus,
			Long SearchStoreId, Integer pageNumber, Integer pageSize) throws BizException;

	public Map<Long, Long> getTaskCountByWorkId(List<Long> ids, List<Integer> taskStatus) throws BizException;

	public List<TaskEntity> findTaskListByEmpId(Long empId, List<Integer> taskStatus) throws BizException;
}
