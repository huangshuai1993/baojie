package com.baojie.manage.back.login.service;

import java.util.List;

import com.baojie.manage.back.login.form.Index_MenuForm;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.exception.BizException;


public interface LoginService {

    public EmployeeDto login(String userName,String password)throws BizException;
    
    public List<Index_MenuForm> getMenus(EmployeeDto employee)throws BizException;

}
