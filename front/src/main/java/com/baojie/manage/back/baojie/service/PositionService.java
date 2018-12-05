package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface PositionService {
	public PageResults<PositionForm> getAllPosition(Integer pageNumber, Integer pageSize, Long towerId)
			throws BizException;

	public Integer addPosition(PositionForm positionForm) throws BizException;

	public Map<String, Object> deletePosition(Long id) throws BizException;
	public Map<String, Object> getPositionInfo(Long id) throws BizException;
}
