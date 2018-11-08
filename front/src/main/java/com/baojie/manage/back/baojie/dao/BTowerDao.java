package com.baojie.manage.back.baojie.dao;

import java.util.List;

import com.baojie.manage.back.baojie.dao.Entity.TowerEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.IEntityDao;
import com.baojie.manage.base.exception.BizException;

public interface BTowerDao extends IEntityDao<TowerEntity> {

	public TowerEntity getTowerByName(String name) throws BizException;

	public PageResults<TowerEntity> getTowerList(Integer pageNo, Integer pageSize) throws BizException;

	public TowerEntity addorUpdateTower(TowerEntity towerEntity) throws BizException;

	public void deleteTower(TowerEntity tower) throws BizException;

	public List<TowerEntity> getTowerListByTowerIds(List<Long> ids) throws BizException;

}
