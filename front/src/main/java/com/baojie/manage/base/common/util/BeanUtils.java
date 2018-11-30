package com.baojie.manage.base.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class BeanUtils extends org.springframework.beans.BeanUtils {
	private BeanUtils() {
	}

	/**
	 * 基于cglib进行对象复制
	 * 
	 * @author shengguofan
	 * @param source
	 *            被复制的对象
	 * @param clazz
	 *            复制对象类型
	 * @return
	 */
	public static <T> T copy(Object source, Class<T> clazz) {
		if (isEmpty(source)) {
			return null;
		}
		T target = instantiate(clazz);
		BeanCopier copier = BeanCopier.create(source.getClass(), clazz, false);
		copier.copy(source, target, null);
		return target;
	}

	/**
	 * 基于cglib进行对象复制
	 *
	 * @param source
	 *            被复制的对象
	 * @param target
	 *            复制对象
	 * @return
	 */
	public static void copy(Object source, Object target) {
		Assert.notNull(source, "The source must not be null");
		Assert.notNull(target, "The target must not be null");
		BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
		copier.copy(source, target, null);
	}

	/**
	 * 基于cglib进行对象组复制
	 * 
	 * @author shengguofan
	 * @param datas
	 *            被复制的对象数组
	 * @param clazz
	 *            复制对象
	 * @return
	 */
	public static <T> List<T> copyByList(List<?> datas, Class<T> clazz) {
		if (isEmpty(datas)) {
			return Collections.emptyList();
		}
		List<T> result = new ArrayList<T>(datas.size());
		for (Object data : datas) {
			result.add(copy(data, clazz));
		}
		return result;
	}

	/**
	 * 利用fastjson进行深拷贝
	 * 
	 * @author shengguofan
	 * @param datas
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> deepCopyByList(List<?> datas, Class<T> clazz) {
		if (isEmpty(datas)) {
			return Collections.emptyList();
		}
		return JSON.parseArray(JSON.toJSONString(datas), clazz);
	}

	/**
	 * 通过class实例化对象
	 * 
	 * @author shengguofan
	 * @param clazz
	 * @return
	 * @throws RuntimeException
	 */
	public static <T> T instantiate(Class<T> clazz) {
		Assert.notNull(clazz, "The class must not be null");
		try {
			return clazz.newInstance();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static <T> boolean isEmpty(T t) {
		if (t == null) {
			return true;
		}
		return StringUtils.isEmpty(t.toString());
	}
	/**
	 * 重写copyProperties
	 * copy不为空的属性
	 * @param source
	 * @param target
	 */
	public static void copyPropertiesNotNUll(Object source, Object target) {
    	Assert.notNull(source, "Source must not be null");
    	Assert.notNull(target, "Target must not be null");
    	Class<?> actualEditable = target.getClass(); 
    	PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    	for (PropertyDescriptor targetPd : targetPds) {
    		if (targetPd.getWriteMethod() != null) {
    			PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(),targetPd.getName());
    				if (sourcePd != null && sourcePd.getReadMethod() != null) {
    					try {
    						Method readMethod = sourcePd.getReadMethod();
    						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
    							readMethod.setAccessible(true);
    						}
    						Object value = readMethod.invoke(source);
    						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
    						if (value != null) {
    							Method writeMethod = targetPd.getWriteMethod();
    							Type targetParameterType = writeMethod.getGenericParameterTypes()[0];
    							//特殊类型不再执行copy XMLGregorianCalendar
    							if(!(targetParameterType.equals(XMLGregorianCalendar.class))){
    								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
    									writeMethod.setAccessible(true);
    								}
    								writeMethod.invoke(target, value);
        						}
        					}
        					}catch (Throwable ex) {
        						throw new FatalBeanException("Could not copy properties from source to target", ex);
        				}
    				}
				}
    		}
    }

}
