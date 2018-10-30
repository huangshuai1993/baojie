package com.baojie.manage.base.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookiesUtil {

    public static String  getCookies(HttpServletRequest httpRequest,String name){
        
        Cookie[] cookies = httpRequest.getCookies(); 
        String value = null;
        if (cookies != null){
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(name)) {
                    value =cookie.getValue();
                    break;
                }
             } 
        }
        return value;
        
    }
}
