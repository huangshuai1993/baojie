package com.baojie.manage.back.organize.service;

import java.util.List;
import java.util.Map;

import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.back.organize.form.ReginAndStoreForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface OrganizeManageService {
	public void updateEnable(Integer status, Long id) throws BizException;

	public PageResults<StoreDto> getStorePageList(String province, String name, Integer pageNumber, Integer pageSize)
			throws BizException;

	public PageResults<WorkerDto> getWorkerPageList(Long storeId, String name, String mobile, Integer status,
			Integer pageNumber, Integer pageSize) throws BizException;

	public List<StoreDto> getStoreList() throws BizException;

	public void addStore(StoreDto dto) throws BizException;

	public void addWorker(WorkerDto dto) throws BizException;

	public void updateStore(StoreDto dto) throws BizException;

	public void updateWorker(WorkerDto dto) throws BizException;

	public StoreDto getStoreInfo(Long id) throws BizException;

	public Map<String, Object> getWorkerInfo(Long id, Long employeeId) throws BizException;

	public void deleteStore(Long id) throws BizException;

	public List<ReginAndStoreForm> getRegionAndStore(Long employeeId) throws BizException;

	public List<WorkerDto> getWorkerListByStoreId(Long storeId) throws BizException;

	public List<StoreDto> getStoreListByStoreId(Long storeId) throws BizException;

	public List<StoreDto> getStoreByIds(List<Long> ids) throws BizException;
}
