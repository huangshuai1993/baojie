package com.baojie.manage.base.dao;

import java.util.List;
import java.util.Map;

import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.DaoException;

public interface IEntityDao<T extends IEntity> {
	public T insert(T entity) throws DaoException;

	public List<T> insertBatch(List<T> entities) throws DaoException;

	public T update(T entity) throws DaoException;

	public List<T> updateBatch(List<T> entities) throws DaoException;

	public void deleteByPK(Long id) throws DaoException;

	public void delete(T entity) throws DaoException;

	public void deleteBatchByPKs(List<Long> ids) throws DaoException;

	public void deleteBatch(List<T> entities) throws DaoException;

	public T selectByPK(Long id) throws DaoException;

	public List<T> selectEntitiesByPKs(List<Long> ids) throws DaoException;

	public List<T> selectList(String hql) throws DaoException;

	public List<T> selectList(String hql, int pageNo, int pageSize) throws DaoException;

	public List<T> selectList(String hql, Map<String, Object> params) throws DaoException;

	public List<T> selectList(String hql, Map<String, Object> params, int pageNo, int pageSize) throws DaoException;

	public long selectRowCount(String hql) throws DaoException;

	public long selectRowCount(String hql, Map<String, Object> params) throws DaoException;

	public T save(T entity) throws DaoException;

	public PageResults<T> listPage(String hql, Map<String, Object> params, int pageNo, int pageSize)
			throws DaoException;
}
