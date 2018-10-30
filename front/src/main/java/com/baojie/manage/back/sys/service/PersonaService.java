package com.baojie.manage.back.sys.service;

import java.util.List;
import java.util.Map;

import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.dto.Employee_personaDto;
import com.baojie.manage.back.sys.dto.PersonaDto;
import com.baojie.manage.back.sys.dto.PowerDto;
import com.baojie.manage.base.exception.BizException;

public interface PersonaService {
	/**
	 * 查询的角色列表
	 * 
	 * @return
	 */
	public List<PersonaDto> getAllPersonas() throws BizException;

	/**
	 * 得到所有的权限
	 * 
	 * @return
	 */
	public List<PowerDto> getAllPower() throws BizException;

	public List<Employee_personaDto> getEmployeeIdBypersonaId(Long personaId) throws BizException;

	public List<EmployeeDto> getEmployeeListByEmployeeIds(List<Long> ids) throws BizException;

	/**
	 * 根据角色id查询权限id
	 */
	public List<Long> getPowerIdByPersonaId(Long personaId) throws BizException;

	/**
	 * 根据powerid查询权限
	 */
	public List<PowerDto> getPowerByPowerId(List<Long> powerId) throws BizException;

	public PersonaDto getPersonaBypersonaId(Long personaId) throws BizException;

	public List<PowerDto> getPowerOne() throws BizException;

	public PersonaDto updateOraddPersonaPower(Long personaId, String personaDesc, String personaName,
			List<Long> powerIds) throws BizException;

	/**
	 * 添加菜单
	 * 
	 * @param power
	 * @return
	 */
	public Map<String, Object> addPowerInfo(PowerDto power) throws BizException;

	public List<EmployeeDto> getEmployeeListBypersonaId(Long personaId) throws BizException;

	public List<PowerDto> getPowerBypersonaId(Long personaId) throws BizException;

}
