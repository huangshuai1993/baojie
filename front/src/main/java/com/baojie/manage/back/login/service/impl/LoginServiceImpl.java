package com.baojie.manage.back.login.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baojie.manage.back.common.enums.LoginExCode;
import com.baojie.manage.back.login.dao.LoginLogDao;
import com.baojie.manage.back.login.form.Index_MenuForm;
import com.baojie.manage.back.login.service.LoginService;
import com.baojie.manage.back.sys.dao.EmployeeDao;
import com.baojie.manage.back.sys.dao.Employee_personaDao;
import com.baojie.manage.back.sys.dao.Persona_powerDao;
import com.baojie.manage.back.sys.dao.PowerDao;
import com.baojie.manage.back.sys.dao.entity.EmployeeEntity;
import com.baojie.manage.back.sys.dao.entity.Employee_personaEntity;
import com.baojie.manage.back.sys.dao.entity.Persona_powerEntity;
import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.dto.PowerDto;
import com.baojie.manage.back.sys.service.convertor.EmployeeConvertor;
import com.baojie.manage.base.common.util.MD5Util;
import com.baojie.manage.base.exception.BizException;
import com.baojie.manage.base.service.BaseService;



@Service("loginService")
public class LoginServiceImpl extends BaseService implements LoginService{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private LoginLogDao loginLogDao;
	@Autowired
	private Employee_personaDao employee_personaDao;
	@Autowired
	private PowerDao powerDao;
	@Autowired
	private Persona_powerDao persona_powerDao;
	
	public EmployeeDto login(String userName, String password) throws BizException {
        EmployeeEntity employee = employeeDao.getEmployeeByUserName(userName);
        if (employee == null) {
            //LoginLogEntity loginEntity = new LoginLogEntity(0L, LoginLogResultEnum.NOTUSER.getId(), "", "");
            //loginLogDao.insert(loginEntity);
            throw new BizException(LoginExCode.NOTUSER);
        };
        String temp_password =  MD5Util.getMD5(password+"{"+employee.getCustNo()+"}");
        if (!temp_password.equals(employee.getPassword())) {
            //LoginLogEntity loginEntity = new LoginLogEntity(employee.getEmployeeId(), LoginLogResultEnum.ERRORPASSWORD.getId(), "","");
           // loginLogDao.insert(loginEntity);
            throw new BizException(LoginExCode.ERRORPASSWORD);
        }
        if (employee != null && "2".equals(employee.getEmpStatus())) {
            //LoginLogEntity loginEntity = new LoginLogEntity(employee.getEmployeeId(), LoginLogResultEnum.DISABLE.getId(), "","");
            //loginLogDao.insert(loginEntity);
            throw new BizException(LoginExCode.DISABLE);
        }
        //LoginLogEntity loginEntity = new LoginLogEntity(employee.getEmployeeId(), LoginLogResultEnum.OK.getId(),"","");
        //loginLogDao.insert(loginEntity);
    	EmployeeConvertor emp=new EmployeeConvertor();
    	EmployeeDto dto = emp.entity2Dto(employee);
        return dto;
    }
    public List<Index_MenuForm> getMenus(EmployeeDto employee) throws BizException {

        Employee_personaEntity employee_personaEntity = employee_personaDao.getEmployee_personaByEmployeeId(employee.getEmployeeId());
        if (employee_personaEntity == null){
             throw new BizException("查询错误");
        }
        //菜单实体
        Index_MenuForm indexMenu = null;
        // 首页菜单集合，返回结果集
        List<Index_MenuForm> reuslt_list = new ArrayList<Index_MenuForm>();
        // 二级菜单
        PowerDto subMenu = null;
        // 一级菜单
        List<PowerEntity> level_one = null;
        // 二级菜单
        List<PowerEntity> level_two = null;
        if ("1".equals(employee.getEmployeeType())){//超级管理员拥有所有权限
            level_one = powerDao.getLevelOne();
            level_two =  powerDao.getPowerLevelTwo();
        } else {
            List<Persona_powerEntity> persona_powerEn = persona_powerDao.getEntityByPersonaId(employee_personaEntity.getPersonaId());
            List<Long> powerId = new ArrayList<Long>();
            if (persona_powerEn != null && !persona_powerEn.isEmpty() ){
                for (Persona_powerEntity p :persona_powerEn){
                    powerId.add(p.getPowerId());
                }
                level_two = powerDao.getPowerByPowerId(powerId);
                PowerEntity pdto = powerDao.getPowerByParentId(1L);
                if (pdto != null){
                    level_two.add(pdto);
                }
                Set<Long> powerOneIdss = new LinkedHashSet<Long>();
                List<Long> powerOneIds = new ArrayList<Long>();
                for (PowerEntity power: level_two){
                    powerOneIdss.add(power.getParentId());
                }
                for (Long id :powerOneIdss){
                    powerOneIds.add(id);
                }
                Collections.sort(powerOneIds);  
                level_one = powerDao.getPowerByPowerId(powerOneIds);
            }
        }
        List<PowerDto> subMenu_list = null;
        if (level_one != null){
            for (PowerEntity classA : level_one) {
                indexMenu = new Index_MenuForm();
                // 遍历二级菜单
                if (level_two !=null && level_two.size() > 0){
                    subMenu_list = new ArrayList<PowerDto>();
                    for (PowerEntity classB : level_two) {
                        if (classA.getPowerId().equals(classB.getParentId())){
                            subMenu = new PowerDto();
                            // 菜单
                            subMenu.setMenuName(classB.getMenuName());
                            // 权限路径
                            subMenu.setPowerUrl(classB.getPowerUrl());
                            // 二级菜单权限
                            subMenu.setPowerName(classB.getPowerName());
                            
                            subMenu.setParentId(classB.getParentId());
                            
                            subMenu.setPowerId(classB.getPowerId());
                            // 添加二级菜单
                            subMenu_list.add(subMenu);
                        }
                    }  
                    indexMenu.setSubMenuName(subMenu_list);
                }
                // 一级菜单和二级菜单组装
                indexMenu.setPowerUrl(subMenu_list.size()>0?subMenu_list.get(0).getPowerUrl():"/service");
                indexMenu.setMenuName(classA.getMenuName());
                indexMenu.setClassName(classA.getPowerName());
                indexMenu.setPowerId(classA.getPowerId());
                reuslt_list.add(indexMenu);  
            }
        }
        return reuslt_list;
    }
}
