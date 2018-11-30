package com.baojie.manage.back.baojie.service;

import java.util.Map;

import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.BizException;

public interface BTowerService {

	public PageResults<TowerForm> getAllTower(Integer pageNumber, Integer pageSize, String towerName, String functionaryName)
			throws BizException;

	public Integer addTower(TowerForm towerForm) throws BizException;

	public Map<String, Object> deleteTower(Long id) throws BizException;
	public Map<String, Object> getTowerInfo(Long id) throws BizException;
}
