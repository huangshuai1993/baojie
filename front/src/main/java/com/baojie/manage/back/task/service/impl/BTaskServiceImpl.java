package com.baojie.manage.back.task.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.common.enums.GeneralExCode;
import com.baojie.manage.back.common.enums.TaskExCode;
import com.baojie.manage.back.organize.dao.StoreDao;
import com.baojie.manage.back.organize.dao.WorkerDao;
import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.back.organize.service.OrganizeManageService;
import com.baojie.manage.back.task.dao.BPhotoDao;
import com.baojie.manage.back.task.dao.BTaskDao;
import com.baojie.manage.back.task.dto.BPhotoDto;
import com.baojie.manage.back.task.dto.BTaskDto;
import com.baojie.manage.back.task.service.BTaskService;
import com.baojie.manage.back.task.service.convertor.BPhotoConvertor;
import com.baojie.manage.back.task.service.convertor.BTaskConvertor;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.dao.entity.PhotoEntity;
import com.baojie.manage.base.dao.entity.StoreEntity;
import com.baojie.manage.base.dao.entity.TaskEntity;
import com.baojie.manage.base.dao.entity.WorkerEntity;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;

@Service("btaskService")
public class BTaskServiceImpl implements BTaskService {
	@Autowired
	private OrganizeManageService organizeManageService;
	@Autowired
	private BTaskDao taskDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private BPhotoDao photoDao;

	@Override
	public PageResults<BTaskDto> getTaskList(String name, String mobile, Long workId, Integer taskStatus,
			Long SearchStoreId, Integer pageNumber, Integer pageSize) throws BizException {
		PageResults<BTaskDto> resultDto = null;
		// 设置workerId集合
		List<Long> workerIds = null;
		List<Long> storeIds = null;
		PageResults<TaskEntity> taskList = taskDao.getTaskList(name, mobile, workId, taskStatus, SearchStoreId,
				pageNumber, pageSize);
		if (taskList != null) {
			List<TaskEntity> list = taskList.getList();
			BTaskConvertor convertor = new BTaskConvertor();
			List<BTaskDto> dtoList = convertor.entity2DtoList(list);
			// 遍历设置workerIds
			workerIds = Lists.newArrayList();
			storeIds = Lists.newArrayList();
			for (BTaskDto bTaskDto : dtoList) {
				if (bTaskDto.getEmpId() != 0) {
					if (!workerIds.contains(bTaskDto.getEmpId())) {
						workerIds.add(bTaskDto.getEmpId());
					}
				}
				if (bTaskDto.getStoreId() != 0) {
					if (!storeIds.contains(bTaskDto.getStoreId())) {
						storeIds.add(bTaskDto.getStoreId());
					}
				}
			}
			// 查询worker对象 setname
			List<WorkerEntity> workerEntity = null;
			List<StoreDto> storeDtos = null;
			if (workerIds.size() > 0) {
				workerEntity = workerDao.selectEntitiesByPKs(workerIds);
			}
			if (storeIds.size() > 0) {
				storeDtos = organizeManageService.getStoreByIds(storeIds);
			}
			for (BTaskDto bTaskDto : dtoList) {
				if (CollectionUtils.isNotEmpty(workerEntity)) {
					for (WorkerEntity workerEntity2 : workerEntity) {
						if (workerEntity2.getId() == bTaskDto.getEmpId()) {
							bTaskDto.setEmpName(workerEntity2.getName());
						}
					}
				}
				if (CollectionUtils.isNotEmpty(storeDtos)) {
					for (StoreDto storeDto : storeDtos) {
						if (storeDto.getId() == bTaskDto.getStoreId()) {
							bTaskDto.setStoreName(storeDto.getName());
							bTaskDto.setStoreProvinceCode(storeDto.getProvinceCode());
						}
					}
				}
			}
			long totalCount = taskList.getTotalCount();
			resultDto = new PageResults<BTaskDto>(dtoList, pageNumber, pageSize, totalCount);
			return resultDto;
		} else {
			throw new BizException(GeneralExCode.NO_QUERY_RESULTS);
		}
	}

	@Override
	public void addBTask(BTaskDto dto) throws BizException {
		try {
			BTaskConvertor btc = new BTaskConvertor();
			TaskEntity entity = btc.dto2Entity(dto);
			taskDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(TaskExCode.TASK_ADD_FAIL);
		}
	}

	@Override
	public void removeTask(Long id, String memo) throws BizException {
		TaskEntity taskEntity = taskDao.selectByPK(id);
		if (taskEntity == null) {
			throw new BizException(TaskExCode.TASK_NULL);
		}
		//taskEntity.setTaskStatus(TaskStatusEnum.REMOVE.getId());
		taskEntity.setMemo(memo);
		taskDao.save(taskEntity);
	}

	@Override
	public void allocatingTask(Long id, Long empId, Long storeId) throws BizException {
		TaskEntity taskEntity = taskDao.selectByPK(id);
		if (taskEntity == null) {
			throw new BizException(TaskExCode.TASK_NULL);
		}
		/*if (TaskStatusEnum.NO_DISTRIBUTION != TaskStatusEnum.getEnum(taskEntity.getTaskStatus())) {
			throw new BizException(TaskExCode.TASK_STATUS_FAIL);
		}
		if (empId != 0) {
			taskEntity.setTaskStatus(TaskStatusEnum.OK_DISTRIBUTION.getId());
		}*/
		taskEntity.setEmpId(empId);
		taskEntity.setStoreId(storeId);
		taskDao.save(taskEntity);
	}

	@Override
	public BTaskDto getTaskInfo(Long id) throws BizException {
		if (id == null) {
			throw new BizException(GeneralExCode.PARAME_NULL);
		}
		TaskEntity entity = taskDao.selectByPK(id);
		BTaskConvertor conver = new BTaskConvertor();
		BTaskDto dto = conver.entity2Dto(entity);
		// 判断门店 及员工 获取name
		if (dto.getStoreId() != 0) {
			StoreEntity storeEntity = storeDao.selectByPK(dto.getStoreId());
			if (storeEntity != null) {
				dto.setStoreName(storeEntity.getName());
				dto.setStoreProvince(SysDqHelper.lookupDQName(storeEntity.getProvinceCode()));
				dto.setStoreProvinceCode(storeEntity.getProvinceCode());
			}
		}
		if (dto.getEmpId() != 0) {
			WorkerEntity workerEntity = workerDao.selectByPK(dto.getEmpId());
			if (workerEntity != null) {
				dto.setEmpName(workerEntity.getName());
			}
		}
		return dto;

	}

	@Override
	public void updateTaskInfo(Long id, Integer taskStatus, Long storeId, Long empId, Integer removeStatus,
			String removeText, Integer passStatus, String passText) throws BizException {
		TaskEntity taskEntity = taskDao.selectByPK(id);
		if (taskEntity == null) {
			throw new BizException(TaskExCode.TASK_NULL);
		}
		if (taskStatus == 0) {// 未分配
			if (storeId != null) {
				taskEntity.setStoreId(storeId);
				if (empId != 0) {// 判断是否未分配员工
					//taskEntity.setTaskStatus(TaskStatusEnum.OK_DISTRIBUTION.getId());
					taskEntity.setEmpId(empId);
				}
			} else {
				taskEntity.setStoreId(0L);
			}
		}
		if (taskStatus < 3) { // 可放弃任务
			if (removeStatus != null && removeStatus != 0) {
				taskEntity.setTaskStatus(removeStatus);
				taskEntity.setMemo(removeText);
				;
			}
		}
		if (taskStatus == 3) {// 是否通过
			if (passStatus != null) {
				taskEntity.setTaskStatus(passStatus);
				taskEntity.setMemo(passText);
			}
		}
		taskDao.save(taskEntity);
	}

	@Override
	public List<BPhotoDto> getPhotoListByTaskId(Long taskId) throws BizException {
		if (taskId == null) {
			throw new BizException(TaskExCode.TASK_ID_NULL);
		}
		List<PhotoEntity> list = photoDao.getPhotoListByTaskId(taskId);
		BPhotoConvertor convertor = new BPhotoConvertor();
		List<BPhotoDto> dtoList = convertor.entity2DtoList(list);
		return dtoList;
	}

	@Override
	public Integer findTaskCountByEmpId(Long empId, List<Integer> taskStatus) throws BizException {
		List<TaskEntity> list = taskDao.findTaskListByEmpId(empId, taskStatus);
		return list.size();
	}
}
