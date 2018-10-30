package com.baojie.manage.base.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        } else {
            if (applicationContext instanceof WebApplicationContext) {
                SpringContextUtil.applicationContext = applicationContext;
            }
        }
	}
	
	public static <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
		return (T) applicationContext.getBean(beanName, requiredType);
    }

}
