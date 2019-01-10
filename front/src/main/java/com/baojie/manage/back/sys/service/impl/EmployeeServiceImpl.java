package com.baojie.manage.back.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baojie.manage.back.common.enums.EmployeeExCode;
import com.baojie.manage.back.sys.dao.EmployeeDao;
import com.baojie.manage.back.sys.dao.Employee_personaDao;
import com.baojie.manage.back.sys.dao.PersonaDao;
import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.dao.entity.PersonaEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.form.PersonaForm;
import com.baojie.manage.back.sys.service.EmployeeService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.MD5Util;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;
import com.github.pagehelper.PageInfo;
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl extends BaseService implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private Employee_personaDao employeePersonaEntityService;
	@Autowired
	private PersonaDao personaDao;
	@Override
	public PageResults<EmployeeDto> getEmployeePageList(String personaName, String parameName, Integer pageNumber,
			Integer pageSize) throws BizException {
		PageResults<EmployeeDto> resultForm = null;
		//empID_personaName_map
		Map<Long,String> empId_personaNameMap=new HashMap<Long,String>();
		//查询所有角色员工关系
		List<Employee_personaEntity> allEmployee_persona = employeePersonaEntityService.queryAll();
		//查询所有角色
		List<PersonaEntity> allPersonas = personaDao.queryAll();
		for (PersonaEntity personaEntity : allPersonas) {
			for (Employee_personaEntity employee_personaEntity : allEmployee_persona) {
				if(personaEntity.getPersonaId() == employee_personaEntity.getPersonaId()){
					empId_personaNameMap.put(employee_personaEntity.getEmployeeId(), personaEntity.getPersonaName());
				}
			}
		}
		List<EmployeeEntity> result = employeeDao.getEmployeeList(pageNumber, pageSize);
		PageInfo<EmployeeEntity> pageInfo = new PageInfo<EmployeeEntity>(result);
		if(!CollectionUtils.isEmpty(result)){
			List<EmployeeDto> dtomList = BeanUtils.copyByList(result, EmployeeDto.class);
			for (EmployeeDto employeeDto : dtomList) {
				employeeDto.setPersoaName(empId_personaNameMap.get(employeeDto.getEmployeeId()));
			}
			long count = pageInfo.getTotal();
			resultForm = new PageResults<EmployeeDto>(dtomList,pageNumber,pageSize,count);
			return resultForm;
		}else{
			throw new BizException(EmployeeExCode.EMPLOYEE_NOT_FOUND);
		}
	}

    @Override
    public EmployeeDto getEmployeeByUserName(String userName) throws BizException {

        EmployeeEntity entity = employeeDao.getEmployeeByUserName(userName);
        if (entity == null) {
            throw new BizException(EmployeeExCode.EMPLOYEE_NOT_FOUND);
        }
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    
    }
	@Override
	public Long addEmployee(EmployeeDto employee, Long personaId) throws BizException{
		String username = employee.getUserName();
		EmployeeEntity entity = employeeDao.getEmployeeByUserName(username);
		if(entity != null){
			return 0l;
		}
		Long employeeId = null;
        Long result = 1l;
        String custNo = employee.getCustNo();
        String password = employee.getPassWord();
        password =  MD5Util.getMD5(password+"{"+custNo+"}");
        if (personaId == 1L){
            employee.setEmployeeType("1");
        }else{
            employee.setEmployeeType("2");
        }
        try {
        	 employee.setPassWord(password);
             // 添加员工
        	 //转换成entity
        	 EmployeeEntity employeeEntity =new EmployeeEntity(employee);
        	 Integer i = employeeDao.saveSelective(employeeEntity);
        	 if(i > 0){
        		 employeeEntity = employeeDao.getEmployeeByUserName(username);
        		 employeeId = employeeEntity.getEmployeeId();
                 // 添加权限
                 Employee_personaEntity employee_persona = new Employee_personaEntity();
                 employee_persona.setEmployeeId(employeeId);
                 employee_persona.setPersonaId(personaId);
                 employeePersonaEntityService.saveSelective(employee_persona);
        	 }
		} catch (Exception e) {
			result = 0l;
			e.printStackTrace();
	        throw new BizException(EmployeeExCode.EMPLOYEE_ADD_FAIL);
		}
       
		return result;
	}

	@Override
	public Map<String, Object> updateEnable(String empStatus, String custNo) throws BizException {
		 Map<String, Object> map = new HashMap<String, Object>();
	     EmployeeEntity employee = employeeDao.getEmployeeByCustNo(custNo);
	        if (employee == null) {
	            map.put(Const.retCode, false);
	            map.put(Const.retMsg, "员工不存在");
	            return map;
	        }
	        employee.setEmpStatus(empStatus);
	        Integer i = employeeDao.updateSelective(employee);
	        if(i > 0){
	        	map.put(Const.retCode, true);
		        map.put(Const.retMsg, "更新成功!");
	        }else{
	        	map.put(Const.retCode, true);
		        map.put(Const.retMsg, "更新失败!");
	        }
	        return map;
	}

	@Override
	public Map<String, Object> updatePassword(String password, String custNo) throws BizException{
		logger.info(String.format("call EmployeeServiceImpl#updatePassword:[custNo=%s]", custNo));
        logger.info("更改员工密码custNo" + custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        EmployeeEntity employee = employeeDao.getEmployeeByCustNo(custNo);
        if (employee == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        password =  MD5Util.getMD5(password+"{"+custNo+"}");
        employee.setPassword(password);
        Integer i = employeeDao.updateSelective(employee);
        if(i > 0){
        	map.put(Const.retCode, true);
	        map.put(Const.retMsg, "修改成功!");
        }else{
        	map.put(Const.retCode, true);
	        map.put(Const.retMsg, "修改失败!");
        }
        return map;
	}

	@Override
	public Map<String, Object> getEmployeeInfo(String custNo) throws BizException{
		logger.info(String.format("call EmployeeServiceImpl#getEmployeeInfo:[custNo=%s]", custNo));
		EmployeeEntity employee = employeeDao.getEmployeeByCustNo(custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (employee == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        //获取角色id
        Employee_personaEntity employee_persona = employeePersonaEntityService.getEmployee_personaByEmployeeId(employee.getEmployeeId());
        Long personaId =employee_persona.getPersonaId();
        if (personaId == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        PersonaEntity personaEntity = personaDao.findPersonaBypersonaId(personaId);
        if(personaEntity==null){
        	map.put(Const.retCode, false);
            map.put(Const.retMsg, "角色信息不存在");
            return map;
        }
        PersonaForm per = new PersonaForm(personaEntity);
        map.put("retCode", true);
        map.put("employee", employee);
        map.put("persona", per);
        return map;
	}

	@Override
	public Map<String, Object> deleteEmployee(String custNo) {
		logger.info(String.format("call EmployeeServiceImpl#deleteEmployee:[custNo=%s]", custNo));
        logger.info("删除员工custNo" + custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	EmployeeEntity employee = employeeDao.getEmployeeByCustNo(custNo);
            if (employee == null) {
                map.put(Const.retCode, false);
                map.put(Const.retMsg, "员工不存在");
                return map;
            }
            if ("1".equals(employee.getEmployeeType())){
                map.put(Const.retCode, false);
                map.put(Const.retMsg, "不能删除超级管理员账号");
                return map; 
            }
            // 删除员工表  
            employeeDao.deleteById(employee.getEmployeeId());
            //删除员工-角色对象关系表
            Employee_personaEntity employee_personaEntity = employeePersonaEntityService.getEmployee_personaByEmployeeId(employee.getEmployeeId());
            employeePersonaEntityService.deleteById(employee_personaEntity.getEmpId());
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
        } catch (Exception e) {
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除失败");
            e.printStackTrace();
         }
        return map;
	}

	@Override
	public Map<String, Object> updateEmployee(EmployeeDto employee, Long personaId) {
		 logger.info(String.format("call EmployeeServiceImpl#updateEmployee:[employee=%s,personaId=%s]",employee,personaId));
	        Map<String, Object> map = new HashMap<String, Object>();
	        try {
	        	EmployeeEntity temp_employee = employeeDao.getEmployeeByCustNo(employee.getCustNo());
	            if (temp_employee == null) {
	                map.put(Const.retCode, false);
	                map.put(Const.retMsg, "员工不存在");
	                return map;
	            }
	            if(!employee.getUserName().equals(temp_employee.getUsername())){
	            	EmployeeEntity EmployeeDto = employeeDao.getEmployeeByUserName(employee.getUserName());
	            	if(EmployeeDto != null){
	            		map.put(Const.retCode, false);
	 	                map.put(Const.retMsg, "员工登录名重复");
	 	                return map;
	            	}
	            	
	            }
	            temp_employee.setUsername(employee.getUserName());
	            temp_employee.setRealName(employee.getRealName());
	            temp_employee.setEmployIDCardNum(employee.getEmployIDCardNum());
	            temp_employee.setPhone(employee.getPhone());
	            temp_employee.setEmployeeType(employee.getEmployeeType());
	            // 更新员工表
	            employeeDao.updateSelective(temp_employee);
	            // 更新员工角色关系表
	            Employee_personaEntity employee_personaEntity = employeePersonaEntityService.getEmployee_personaByEmployeeId(employee.getEmployeeId());
	            employee_personaEntity.setPersonaId(personaId);
	            employeePersonaEntityService.updateSelective(employee_personaEntity);
	            map.put(Const.retCode, true);
	            map.put(Const.retMsg, "更新成功");
	        } catch (Exception e) {
	            map.put(Const.retCode, true);
	            map.put(Const.retMsg, "更新失败");
	            e.printStackTrace();
	        }
	        return map;
	}



}
