package com.baojie.manage.back.organize.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.common.enums.EmployeeExCode;
import com.baojie.manage.back.common.enums.GeneralExCode;
import com.baojie.manage.back.common.enums.OrganizeExCode;
import com.baojie.manage.back.organize.dao.StoreDao;
import com.baojie.manage.back.organize.dao.WorkerDao;
import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.back.organize.form.ReginAndStoreForm;
import com.baojie.manage.back.organize.form.StoreForm;
import com.baojie.manage.back.organize.form.WorkerForm;
import com.baojie.manage.back.organize.service.OrganizeManageService;
import com.baojie.manage.back.organize.service.convertor.StoreConvertor;
import com.baojie.manage.back.organize.service.convertor.WorkerConvertor;
import com.baojie.manage.back.sys.dao.EmployeeDao;
import com.baojie.manage.back.sys.dao.Employee_personaDao;
import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.form.Region;
import com.baojie.manage.back.task.dao.BTaskDao;
import com.baojie.manage.base.common.consts.CacheKeyConst;
import com.baojie.manage.base.common.util.GetUniqueNoUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.common.util.Utils;
import com.baojie.manage.base.dao.entity.StoreEntity;
import com.baojie.manage.base.dao.entity.WorkerEntity;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("organizeManageService")
public class OrganizeManageServiceImpl extends BaseService implements OrganizeManageService {
	@Autowired
	private BTaskDao taskDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private Employee_personaDao employee_personaDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, StoreEntity> valueOperations;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Object> redis;

	@Override
	public List<StoreDto> getStoreByIds(List<Long> ids) throws BizException {
		List<StoreEntity> result = Lists.newArrayList();
		StoreEntity storeEntity = null;
		// 缓存中不存在的ID
		List<Long> cacheNotNuLLIds = Lists.newArrayList();
		for (Long id : ids) {
			storeEntity = this.valueOperations.get(CacheKeyConst.STORE_CACHE_KEY + id);
			if (storeEntity != null) {
				result.add(storeEntity);
			} else {
				cacheNotNuLLIds.add(id);
			}
		}
		if (CollectionUtils.isNotEmpty(cacheNotNuLLIds)) {
			List<StoreEntity> entitiesByPKs = storeDao.selectEntitiesByPKs(cacheNotNuLLIds);
			if (CollectionUtils.isNotEmpty(entitiesByPKs)) {
				for (StoreEntity storeEntity2 : entitiesByPKs) {
					result.add(storeEntity2);
					this.valueOperations.set(CacheKeyConst.STORE_CACHE_KEY + storeEntity2.getId(), storeEntity2,
							Utils.WEEK, TimeUnit.MILLISECONDS);
				}
			}
		}
		StoreConvertor conver = new StoreConvertor();
		return conver.entity2DtoList(result);
	}

	@Override
	public PageResults<StoreDto> getStorePageList(String province, String name, Integer pageNumber, Integer pageSize)
			throws BizException {
		PageResults<StoreDto> resultDto = null;
		PageResults<StoreEntity> storeList = storeDao.getStoreList(province, name, pageNumber, pageSize);
		// 处理转化dto
		StoreConvertor sc = new StoreConvertor();
		if (storeList != null) {
			List<StoreEntity> list = storeList.getList();
			List<StoreDto> entity2DtoList = sc.entity2DtoList(list);
			long totalCount = storeList.getTotalCount();
			resultDto = new PageResults<StoreDto>(entity2DtoList, pageNumber, pageSize, totalCount);
			return resultDto;
		} else {
			throw new BizException(GeneralExCode.NO_QUERY_RESULTS);
		}
	}

	@Override
	public List<StoreDto> getStoreList() throws BizException {
		StoreConvertor con = new StoreConvertor();
		List<StoreEntity> entList = storeDao.selectList("from StoreEntity where status = 1");
		List<StoreDto> dtoList = con.entity2DtoList(entList);
		return dtoList;
	}

	@Override
	public void addStore(StoreDto dto) throws BizException {
		StoreEntity entity = storeDao.getStoreByName(dto.getName());
		if (entity != null) {
			throw new BizException(OrganizeExCode.STORE_ADD_REPEAT);
		}
		try {
			StoreEntity store = new StoreEntity();
			store.setName(dto.getName());
			store.setProvinceCode(dto.getProvinceCode());
			StoreEntity storeEntity = storeDao.save(store);
			// 存储缓存
			this.valueOperations.set(CacheKeyConst.STORE_CACHE_KEY + storeEntity.getId(), storeEntity, Utils.WEEK,
					TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(OrganizeExCode.STORE_ADD_FAIL);
		}
	}

	@Override
	public void updateStore(StoreDto dto) throws BizException {
		logger.info(String.format("call StoreServiceImpl#updateStore:[StoreDto=%s]", dto));
		StoreEntity entity = storeDao.selectByPK(dto.getId());
		if (entity == null) {
			throw new BizException(OrganizeExCode.STORE_NULL);
		}
		if (!entity.getName().equals(dto.getName())) {
			StoreEntity tmpEntity = storeDao.getStoreByName(dto.getName());
			if (tmpEntity != null) {
				throw new BizException(OrganizeExCode.STORE_ADD_REPEAT);
			}
		}
		entity.setName(dto.getName());
		entity.setProvinceCode(dto.getProvinceCode());
		StoreEntity storeEntity = storeDao.save(entity);
		this.valueOperations.set(CacheKeyConst.STORE_CACHE_KEY + storeEntity.getId(), storeEntity, Utils.WEEK,
				TimeUnit.MILLISECONDS);
	}

	@Override
	public StoreDto getStoreInfo(Long id) throws BizException {
		logger.info(String.format("call StoreServiceImpl#getStoreInfo:[id=%s]", id));
		StoreEntity storeEntity = null;
		storeEntity = this.valueOperations.get(CacheKeyConst.STORE_CACHE_KEY + id);
		if (storeEntity == null) {
			storeEntity = storeDao.selectByPK(id);
		}
		if (storeEntity == null) {
			throw new BizException(OrganizeExCode.STORE_NULL);
		}
		this.valueOperations.set(CacheKeyConst.STORE_CACHE_KEY + storeEntity.getId(), storeEntity, Utils.WEEK,
				TimeUnit.MILLISECONDS);
		StoreConvertor con = new StoreConvertor();
		return con.entity2Dto(storeEntity);
	}

	@Override
	public void deleteStore(Long id) throws BizException {
		logger.info(String.format("call StoreServiceImpl#deleteStore:[id=%s]", id));
		StoreEntity entity = storeDao.selectByPK(id);
		if (entity == null) {
			throw new BizException(OrganizeExCode.STORE_NULL);
		}
		List<WorkerEntity> WorkerList = workerDao.getWorkerByStoreId(entity.getId());
		if (WorkerList.size() > 0) {
			throw new BizException(OrganizeExCode.STORE_DELETE_INFO);
		}
		this.valueOperations.getOperations().delete(CacheKeyConst.STORE_CACHE_KEY + entity.getId());
		storeDao.delete(entity);
	}

	@Override
	public PageResults<WorkerDto> getWorkerPageList(Long storeId, String name, String mobile, Integer status,
			Integer pageNumber, Integer pageSize) throws BizException {
		PageResults<WorkerDto> resultDto = null;
		PageResults<WorkerEntity> workerList = workerDao.getWorkerList(storeId, name, mobile, status, pageNumber,
				pageSize);
		// 处理转化dto
		if (workerList != null) {
			List<WorkerEntity> list = workerList.getList();
			WorkerConvertor wp = new WorkerConvertor();
			List<WorkerDto> dtoList = wp.entity2DtoList(list);
			// 定义id List
			List<Long> ids = Lists.newArrayList();
			for (WorkerDto workerDto : dtoList) {// 已分配
				ids.add(workerDto.getId());
			}
			List<Integer> taskStatus = Lists.newArrayList();
			/*taskStatus.add(TaskStatusEnum.OK_DISTRIBUTION.getId());
			taskStatus.add(TaskStatusEnum.NO_PAY.getId());*/
			if (ids.size() > 0) {
				Map<Long, Long> map = taskDao.getTaskCountByWorkId(ids, taskStatus);
				for (WorkerDto workerDto : dtoList) {// 根据id 获取任务量
					workerDto.setTaskCount(map.get(workerDto.getId()));
				}
			}

			long totalCount = workerList.getTotalCount();
			resultDto = new PageResults<WorkerDto>(dtoList, pageNumber, pageSize, totalCount);
			return resultDto;
		} else {
			throw new BizException(GeneralExCode.NO_QUERY_RESULTS);
		}
	}

	@Override
	public void addWorker(WorkerDto dto) throws BizException {
		WorkerEntity entity = workerDao.getWorkerByIdCard(dto.getIdcardNo());
		if (entity != null) {
			throw new BizException(OrganizeExCode.WORKER_IDCARD_REPEAT);
		}
		WorkerEntity entity1 = workerDao.getWorkerByMobile(dto.getMobile());
		if (entity1 != null) {
			throw new BizException(OrganizeExCode.WORKER_ADD_REPEAT);
		}
		try {
			// 设置密码 随机数
			String randomCode = GetUniqueNoUtil.getRandomCode(12);
			dto.setPassword(randomCode);
			WorkerConvertor wp = new WorkerConvertor();
			WorkerEntity dto2Entity = wp.dto2Entity(dto);
			workerDao.save(dto2Entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(OrganizeExCode.WORKER_ADD_FAIL);
		}
	}

	@Override
	public void updateWorker(WorkerDto dto) throws BizException {
		logger.info(String.format("call organizeManageService#updateWorker:[WorkerDto=%s]", dto));
		WorkerEntity entity = workerDao.selectByPK(dto.getId());
		if (entity == null) {
			throw new BizException(OrganizeExCode.WORKER_NULL);
		}
		if (!entity.getIdcardNo().equals(dto.getIdcardNo())) {
			WorkerEntity tmpworker = workerDao.getWorkerByIdCard(dto.getIdcardNo());
			if (tmpworker != null) {
				throw new BizException(OrganizeExCode.WORKER_IDCARD_REPEAT);
			}
		}
		entity.setName(dto.getName());
		entity.setIdcardNo(dto.getIdcardNo());
		entity.setBirthday(dto.getBirthday());
		entity.setMobile(dto.getMobile());
		entity.setEmpNo(dto.getEmpNo());
		entity.setEmail(dto.getEmail());
		entity.setStoreId(dto.getStoreId());
		entity.setStatus(dto.getStatus());
		entity.setGender(dto.getGender());
		workerDao.save(entity);
		this.loginOut(entity.getId().toString());
	}

	@Override
	public void updateEnable(Integer status, Long id) throws BizException {
		WorkerEntity entity = workerDao.selectByPK(id);
		if (entity == null) {
			throw new BizException(OrganizeExCode.WORKER_NULL);
		}
		entity.setStatus(status);
		workerDao.save(entity);
		String token = (String) redis.get(entity.getId().toString());
		if (StringUtils.isNotBlank(token)) {
			redis.set(token, entity);
		}
	}

	/**
	 * 取消前台登录状态
	 * 
	 * @param id
	 */
	private void loginOut(String id) {
		String token = (String) redis.get(id);
		if (StringUtils.isNotBlank(token)) {
			redis.getOperations().delete(token);
			redis.getOperations().delete(id);
		}
	}

	@Override
	public Map<String, Object> getWorkerInfo(Long id, Long employeeId) throws BizException {
		logger.info(String.format("call organizeManageService#getWorkerInfo:[id=%s]", id));
		WorkerEntity entity = workerDao.selectByPK(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity == null) {
			throw new BizException(OrganizeExCode.WORKER_NULL);
		}
		WorkerForm form = new WorkerForm(entity);
		Employee_personaEntity ep = employee_personaDao.getEmployee_personaByEmployeeId(employeeId);
		if (ep.getPersonaId() == 1) {// 超级管理员 //返回地区 及门店id
			StoreEntity storeEntity = storeDao.selectByPK(entity.getStoreId());
			if (storeEntity != null) {
				StoreForm storeForm = new StoreForm(storeEntity);
				map.put("storeForm", storeForm);
			}
		}
		map.put("retCode", true);
		map.put("worker", form);
		return map;
	}

	@Override
	public List<ReginAndStoreForm> getRegionAndStore(Long employeeId) throws BizException {
		if (employeeId == null) {
			throw new BizException(EmployeeExCode.EMPLOYEE_NOT_FOUND);
		}
		List<ReginAndStoreForm> regionAndStore = null;
		ReginAndStoreForm form = null;
		Employee_personaEntity entity = employee_personaDao.getEmployee_personaByEmployeeId(employeeId);
		if (entity != null) {
			regionAndStore = Lists.newArrayList();
			if (entity.getPersonaId() == 1) {// 超级管理员
				// 获取所有省份
				List<Region> list = SysDqHelper.lookDqByParentCode("");
				// 获取所有门店
				String hql = "from StoreEntity s order by s.id asc";
				List<StoreEntity> storeList = storeDao.selectList(hql);
				for (Region region2 : list) {
					form = new ReginAndStoreForm(region2.getRegion_id(), region2.getRegion_name());
					regionAndStore.add(form);
					List<StoreForm> storeformList = form.getStoreList();
					for (StoreEntity storeEntity : storeList) {
						if (region2.getRegion_id().equals(storeEntity.getProvinceCode())) {
							storeformList.add(new StoreForm(storeEntity));
						}
					}
				}

			} else if (entity.getPersonaId() == 2) {// 门店主管
				EmployeeEntity employee = employeeDao.selectByPK(employeeId);
				StoreEntity store = new StoreEntity();
						//storeDao.selectByPK(employee.getStoreId());
				form = new ReginAndStoreForm(store.getProvinceCode(),
						SysDqHelper.lookupDQName(store.getProvinceCode()));
				StoreForm storeForm = new StoreForm();
				storeForm.setId(store.getId());
				storeForm.setName(store.getName());
				storeForm.setProvinceCode(store.getProvinceCode());
				List<StoreForm> storeList = form.getStoreList();
				storeList.add(storeForm);
				regionAndStore.add(form);
			}
		} else {
			throw new BizException(EmployeeExCode.EMPLOYEE_NOT_FOUND);
		}
		return regionAndStore;
	}

	@Override
	public List<WorkerDto> getWorkerListByStoreId(Long storeId) throws BizException {
		List<WorkerEntity> list = workerDao.getWorkerByStoreId(storeId);
		WorkerConvertor wc = new WorkerConvertor();
		List<WorkerDto> dtoList = wc.entity2DtoList(list);
		return dtoList;
	}

	@Override
	public List<StoreDto> getStoreListByStoreId(Long storeId) {
		Map<String, Object> params = Maps.newHashMap();
		String hql = null;
		if (storeId != null) {
			hql = "from StoreEntity s where  s.id=:storeId  order by s.id asc ";
			params.put("storeId", storeId);
		} else {
			hql = "from StoreEntity w order by w.created desc ";
		}
		List<StoreEntity> list = storeDao.selectList(hql, params);
		StoreConvertor sc = new StoreConvertor();
		List<StoreDto> dtoList = sc.entity2DtoList(list);
		return dtoList;
	}

}
