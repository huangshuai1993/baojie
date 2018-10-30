package com.baojie.manage.back.sys.dao;

import java.util.List;

import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface PowerDao extends IEntityDao<PowerEntity> {
	public List<PowerEntity> getPowerDatas() throws BizException;

	/**
	 * 二级菜单!=null
	 * 
	 * @return
	 * @throws BizException
	 */
	public List<PowerEntity> getPowerLevelTwo() throws BizException;

	/**
	 * 一级菜单 =null
	 * 
	 * @return
	 */
	public List<PowerEntity> getLevelOne() throws BizException;

	/**
	 * 根据id获取power
	 * 
	 * @param powerId
	 * @return
	 * @throws BizException
	 */
	public List<PowerEntity> getPowerByPowerId(List<Long> powerId) throws BizException;

	public PowerEntity getPowerByParentId(Long parentId) throws BizException;
}
