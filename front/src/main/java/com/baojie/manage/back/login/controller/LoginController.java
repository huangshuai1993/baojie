package com.baojie.manage.back.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.login.form.Index_MenuForm;
import com.baojie.manage.back.login.service.LoginService;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.MD5Util;
import com.baojie.manage.base.controller.BaseController;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    
    @Autowired
    private LoginService loginService;
    
    @Resource(name = "redisTemplate")
    protected ValueOperations<String, List<Index_MenuForm>> Index_MenuCache;
    @Resource(name = "redisTemplate")
    protected ValueOperations<String, Object> redis;
    
    @RequestMapping("/toLogin")
    public String logs(HttpServletRequest request) {
        return "login/login";

    }
    /**
     * 登录
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/logins")
    @ResponseBody
    public  Map<String, Object> login(HttpServletRequest request, HttpServletResponse response,String userName,String password) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("retCode", true);
        EmployeeDto dto = null;
        dto = loginService.login(userName, password);
        String token = MD5Util.getMD5(userName+dto.getCustNo()+System.currentTimeMillis()+"addit");
        addCookie(Const.TOKEN, token, 3600, "/", response);
        addCookie(Const.mainIndexCookie, "0", -1, "/", response);
        addCookie(Const.mainPowerCookie, "1", -1, "/", response);
        addCookie(Const.subIndexCookie, "0", -1, "/", response);
        List<Index_MenuForm> form = null;
        form = loginService.getMenus(dto);
        Index_MenuCache.set(Const.MENU+token,form);
        redis.set(token, dto);
        return result;

    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        String token = getCookieVal(Const.TOKEN);
        if (token != null){
            redis.getOperations().delete(token);
        }
        delCookie(Const.TOKEN, "/", response);
        delCookie(Const.mainPowerCookie, "/", response);
        delCookie(Const.mainIndexCookie, "/", response);
        delCookie(Const.subIndexCookie, "/", response);
        return "/login/login";
        
    }
    /**
     * 登陆成功进行处理的方法
     * 
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request,Long powerId) {
        return "login/index";
    }

}
