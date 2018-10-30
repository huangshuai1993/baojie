package com.baojie.manage.back.task.service;

import java.util.List;

import com.baojie.manage.back.task.dto.BPhotoDto;
import com.baojie.manage.back.task.dto.BTaskDto;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BTaskService {
	 public PageResults<BTaskDto> getTaskList(String name,String mobile,Long workId,Integer taskStatus,Long SearchStoreId,Integer pageNumber, Integer pageSize)throws BizException;
	 
	 public void addBTask(BTaskDto dto) throws BizException;
	 
	 public void removeTask(Long id,String memo) throws BizException;
	 public void allocatingTask(Long id,Long empId,Long storeId) throws BizException;
	 public void updateTaskInfo(Long id,Integer taskStatus,Long storeId,
			 Long empId,Integer removeStatus,String removeText,Integer passStatus,
			 String passText) throws BizException;
	 public BTaskDto getTaskInfo(Long id) throws BizException;
	 
	 public List<BPhotoDto> getPhotoListByTaskId (Long taskId)throws BizException;
	 
	 public Integer findTaskCountByEmpId (Long empId,List<Integer> taskStatus) throws BizException;
}
