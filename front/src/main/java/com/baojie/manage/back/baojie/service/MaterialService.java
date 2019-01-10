package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.MaterialForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface MaterialService {
	public PageResults<MaterialForm> getAllMaterial(Integer pageNumber, Integer pageSize, Long towerId,String beginTime,String endTime)
			throws BizException;

	public Integer addMaterial(MaterialForm materialForm) throws BizException;

	public Map<String, Object> deleteMaterial(Long id) throws BizException;
	public Map<String, Object> getMaterialInfo(Long id) throws BizException;
}
