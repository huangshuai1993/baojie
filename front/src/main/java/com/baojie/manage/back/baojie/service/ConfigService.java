package com.baojie.manage.back.baojie.service;

import java.util.List;
import java.util.Map;

import com.baojie.manage.back.baojie.form.ConfigDetailForm;
import com.baojie.manage.back.baojie.form.ConfigForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface ConfigService {
	public PageResults<ConfigForm> getAllConfig(Integer pageNumber, Integer pageSize, String describe) throws BizException;
	
	public PageResults<ConfigDetailForm> getAllConfigDetail(Integer pageNumber, Integer pageSize, String configuration,String desc) throws BizException; 
	
	public Map<String, Object> addOrUpdateConfig(ConfigForm configForm) throws BizException;
	
	public Map<String, Object> addOrUpdateConfigDetail(ConfigDetailForm configDetailForm) throws BizException;
	
	public Map<String, Object> getInfoConfig(Long id) throws BizException;
	
	public Map<String, Object> getInfoConfigDetail(Long id) throws BizException;
	
	public Map<String, Object> deleteConfig(Long id) throws BizException;
	public Map<String, Object> deleteConfigDetail(Long id) throws BizException;
	
	public List<ConfigForm> queryConfigAll()throws BizException;
	
}



