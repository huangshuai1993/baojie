package com.baojie.manage.back.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.common.enums.EmployeeExCode;
import com.baojie.manage.back.common.enums.PersonaExCode;
import com.baojie.manage.back.common.enums.PersonaPowerExCode;
import com.baojie.manage.back.sys.dao.EmployeeDao;
import com.baojie.manage.back.sys.dao.Employee_personaDao;
import com.baojie.manage.back.sys.dao.PersonaDao;
import com.baojie.manage.back.sys.dao.Persona_powerDao;
import com.baojie.manage.back.sys.dao.PowerDao;
import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.dto.Employee_personaDto;
import com.baojie.manage.back.sys.dto.PersonaDto;
import com.baojie.manage.back.sys.dto.PowerDto;
import com.baojie.manage.back.sys.service.PersonaService;
import com.baojie.manage.back.sys.service.convertor.EmployeeConvertor;
import com.baojie.manage.back.sys.service.convertor.Employee_personaConvertor;
import com.baojie.manage.back.sys.service.convertor.PersonaConvertor;
import com.baojie.manage.back.sys.service.convertor.PowerConvertor;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.google.common.collect.Lists;

@Service("personaService")
public class PersonaServiceImpl extends BaseService implements PersonaService {

	@Resource(name = "redisTemplate")
	protected ValueOperations<String, String> redisCache;
	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private PowerDao powerDao;
	@Autowired
	private Employee_personaDao employee_personaDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private Persona_powerDao persona_powerDao;

	@Override
	public List<PersonaDto> getAllPersonas() throws BizException {

		List<PersonaEntity> allPersona = personaDao.getAllPersonas();
		if (allPersona == null) {
			throw new BizException(EmployeeExCode.PERSONA_NOT_FOUND.code(), EmployeeExCode.PERSONA_NOT_FOUND.message());
		}
		PersonaConvertor p = new PersonaConvertor();
		List<PersonaDto> dtoList = p.entity2DtoList(allPersona);
		return dtoList;
	}

	@Override
	public List<PowerDto> getAllPower() throws BizException {
		List<PowerEntity> powerDatas = powerDao.getPowerDatas();
		List<PowerDto> dtoList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(powerDatas)) {
			PowerConvertor p = new PowerConvertor();
			dtoList = p.entity2DtoList(powerDatas);
		}
		return dtoList;
	}

	@Override
	public List<Employee_personaDto> getEmployeeIdBypersonaId(Long personaId) throws BizException {
		List<Employee_personaEntity> list = employee_personaDao.getEmployeeIdBypersonaId(personaId);
		if (list == null) {
			throw new BizException(PersonaPowerExCode.EMPLOYEE_NOT_FOUND.code(),
					PersonaPowerExCode.EMPLOYEE_NOT_FOUND.message());
		}
		Employee_personaConvertor p = new Employee_personaConvertor();
		List<Employee_personaDto> dtoList = p.entity2DtoList(list);
		return dtoList;
	}

	@Override
	public List<EmployeeDto> getEmployeeListByEmployeeIds(List<Long> ids) throws BizException {
		List<EmployeeEntity> employeeListByEmployeeIds = employeeDao.getEmployeeListByEmployeeIds(ids);
		EmployeeConvertor ec = new EmployeeConvertor();
		List<EmployeeDto> dtoList = ec.entity2DtoList(employeeListByEmployeeIds);
		/*
		 * List<EmployeeForm> eForm = Lists.newArrayList(); if
		 * (CollectionUtils.isNotEmpty(eDtoList)){ for (EmployeeDto eto :
		 * eDtoList){ eForm.add(new EmployeeForm(eto)); } }
		 */
		return dtoList;
	}

	@Override
	public List<Long> getPowerIdByPersonaId(Long personaId) throws BizException {
		List<Persona_powerEntity> entityByPersonaId = persona_powerDao.getEntityByPersonaId(personaId);
		List<Long> PowerIds = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(entityByPersonaId)) {
			for (Persona_powerEntity pto : entityByPersonaId) {
				PowerIds.add(pto.getPowerId());
			}
		}
		return PowerIds;
	}

	@Override
	public List<PowerDto> getPowerByPowerId(List<Long> powerId) throws BizException {
		List<PowerEntity> powerByPowerId = powerDao.getPowerByPowerId(powerId);
		PowerConvertor p = new PowerConvertor();
		List<PowerDto> dtoList = p.entity2DtoList(powerByPowerId);
		/*
		 * List<PowerForm> pFormList = Lists.newArrayList(); if
		 * (CollectionUtils.isNotEmpty(pDtoList)){ for (PowerDto pto :
		 * pDtoList){ pFormList.add(new PowerForm(pto)); } }
		 */
		return dtoList;
	}

	@Override
	public PersonaDto getPersonaBypersonaId(Long personaId) throws BizException {
		PersonaEntity entity = personaDao.findPersonaBypersonaId(personaId);
		PersonaDto pDto = null;
		if (entity != null) {
			pDto = new PersonaConvertor().entity2Dto(entity);
		}
		return pDto;
	}

	@Override
	public List<PowerDto> getPowerOne() throws BizException {
		List<PowerEntity> list = powerDao.getLevelOne();
		List<PowerDto> pDtoList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			pDtoList = new PowerConvertor().entity2DtoList(list);
		}
		/*
		 * List<PowerForm> pFormList = Lists.newArrayList(); if
		 * (CollectionUtils.isNotEmpty(pDtoList)){ for (PowerDto pto :
		 * pDtoList){ pFormList.add(new PowerForm(pto)); } }
		 */
		return pDtoList;
	}

	@Override
	public PersonaDto updateOraddPersonaPower(Long personaId, String personaDesc, String personaName,
			List<Long> powerIds) throws BizException {
		// 根据角色id查询是否是添加和修改
		PersonaEntity pEntity = null;
		if (personaId != null) {// 更新
			List<Persona_powerEntity> pe = persona_powerDao.getEntityByPersonaId(personaId);
			if (CollectionUtils.isNotEmpty(pe)) {
				persona_powerDao.deleteBatch(pe);
			}
		} else {// 添加
			List<PersonaEntity> result = personaDao.findPersonaBypersonaName(personaName);
			if (CollectionUtils.isNotEmpty(result)) {
				throw new BizException(PersonaExCode.PERSONA_ISEXIST);
			}
			pEntity = new PersonaEntity();
			pEntity.setPersonaDesc(personaDesc);
			pEntity.setPersonaEnable("1");
			pEntity.setPersonaName(personaName);
			pEntity = personaDao.save(pEntity);
			personaId = pEntity.getPersonaId();
		}
		// 重新添加权限
		if (CollectionUtils.isNotEmpty(powerIds)) {
			for (Long tempLong : powerIds) {
				Persona_powerEntity personapower = new Persona_powerEntity();
				personapower.setPersonaId(personaId);
				personapower.setPowerId(tempLong);
				persona_powerDao.save(personapower);
			}
		}
		return new PersonaConvertor().entity2Dto(pEntity);
	}

	@Override
	public Map<String, Object> addPowerInfo(PowerDto power) throws BizException {
		Map<String, Object> map = new HashMap<>();
		try {

			powerDao.save(new PowerConvertor().dto2Entity(power));
			redisCache.getOperations().delete(Const.MENU);
			map.put(Const.retCode, true);
			map.put(Const.retMsg, "添加成功");
		} catch (Exception e) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "添加失败");
		}
		return map;
	}

	@Override
	public List<EmployeeDto> getEmployeeListBypersonaId(Long personaId) throws BizException {

		List<Employee_personaEntity> employeeIdBypersonaId = employee_personaDao.getEmployeeIdBypersonaId(personaId);
		List<Long> employeeIds = Lists.newArrayList();
		List<EmployeeDto> dtoList = null;
		if (CollectionUtils.isNotEmpty(employeeIdBypersonaId)) {
			for (Employee_personaEntity epentity : employeeIdBypersonaId) {
				employeeIds.add(epentity.getEmployeeId());
			}
			List<EmployeeEntity> entitieList = employeeDao.selectEntitiesByPKs(employeeIds);
			dtoList = new EmployeeConvertor().entity2DtoList(entitieList);
		}
		return dtoList;
	}

	@Override
	public List<PowerDto> getPowerBypersonaId(Long personaId) throws BizException {

		List<Persona_powerEntity> entityList = persona_powerDao.getEntityByPersonaId(personaId);
		List<Long> powerIds = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (Persona_powerEntity epDto : entityList) {
				powerIds.add(epDto.getPowerId());
			}
			List<PowerEntity> powerList = powerDao.selectEntitiesByPKs(powerIds);
			List<PowerDto> dtoList = new PowerConvertor().entity2DtoList(powerList);
			return dtoList;
		}
		return null;
	}

}
