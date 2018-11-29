package com.baojie.manage.base.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.exception.DaoException;

public abstract class AbstractHibernateEntityDao<T extends IEntity> {

	private static final Logger logger = Logger.getLogger(AbstractHibernateEntityDao.class);

	private Class<T> clazz;
	private String tableName;
	private String pkName;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	protected AbstractHibernateEntityDao() {
		this.clazz = getClazz(getClass().getGenericSuperclass());
		if (this.clazz == null) {
			// 原因: AOP生成代理子类没有泛型这时需要cglib类再一次获取原始类的泛型
			this.clazz = getClazz(getClass().getSuperclass().getGenericSuperclass());
		}
		if (this.clazz == null) {
			throw new RuntimeException(getClass() + " get T class fail");
		}
		this.tableName = clazz.getSimpleName();
		this.pkName = getPkName(clazz);
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClazz(Type type) {
		if (type instanceof ParameterizedType) {
			Type[] array = ((ParameterizedType) type).getActualTypeArguments();
			return (Class<T>) array[0];
		}
		return null;
	}

	private String getPkName(Class<T> clazz) {
		String pkName = null;
		List<Field> fields = new ArrayList<Field>();
		if (clazz != null) {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		}
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Id) {
					pkName = field.getName();
					break;// 主键字段只支持一个注解
				}
			}
		}
		return pkName;
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public T insert(T entity) throws DaoException {
		hibernateTemplate.save(entity);
		return entity;
	}

	public List<T> insertBatch(List<T> entities) throws DaoException {
		List<T> list = new ArrayList<T>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (T entity : entities) {
				hibernateTemplate.save(entity);
				list.add(entity);
			}
		}
		return list;
	}

	public T update(T entity) throws DaoException {
		hibernateTemplate.update(entity);
		return entity;
	}

	public List<T> updateBatch(List<T> entities) throws DaoException {
		List<T> list = new ArrayList<T>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (T entity : entities) {
				hibernateTemplate.update(entity);
				list.add(entity);
			}
		}
		return list;
	}

	public void deleteByPK(Long id) throws DaoException {
		T entity = selectByPK(id);
		if (entity != null) {
			hibernateTemplate.delete(entity);
		}
	}

	public void delete(T entity) throws DaoException {
		hibernateTemplate.delete(entity);
	}

	public void deleteBatchByPKs(final List<Long> ids) throws DaoException {
		if (CollectionUtils.isEmpty(ids)) {
			throw new DaoException("HibernateEntityDao deleteBatchByPKs method params ids is empty");
		}
		this.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				String hql = "delete from " + tableName + " e where e." + pkName + " in (:ids)";
				try {
					Query query = session.createQuery(hql);
					query.setParameterList("ids", ids);
					query.executeUpdate();
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		});
	}

	public void deleteBatch(List<T> entities) throws DaoException {
		if (CollectionUtils.isEmpty(entities)) {
			throw new DaoException("HibernateEntityDao deleteBatch method params entities is empty");
		}
		for (T entity : entities) {
			hibernateTemplate.delete(entity);
		}
	}

	public T selectByPK(Long id) throws DaoException {
		return hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectEntitiesByPKs(final List<Long> ids) throws DaoException {
		if (CollectionUtils.isEmpty(ids)) {
			throw new DaoException("HibernateEntityDao selectEntitiesByPKs method params ids is empty");
		}

		List<T> list = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				String hql = "from " + tableName + " e where e." + pkName + " in (:ids)";
				Query query = session.createQuery(hql);
				query.setParameterList("ids", ids);
				return query.list();
			}
		});

		return sort(ids, list);
	}

	@SuppressWarnings("unchecked")
	private List<T> sort(List<Long> ids, List<T> datas) {
		List<T> result = new ArrayList<T>();
		if (ids.size() == 1) {
			return datas;
		}
		List<IEntity> list = new ArrayList<IEntity>();
		for (T data : datas) {
			list.add(data);
		}

		Map<Long, IEntity> map = new HashMap<Long, IEntity>();
		for (IEntity entity : list) {
			map.put(entity.pkValue(), entity);
		}

		for (Long id : ids) {
			IEntity entity = map.get(id);
			if (entity != null) {
				result.add((T) entity);
			}
		}
		return result;
	}

	public List<T> selectList(String hql) throws DaoException {
		return selectList(hql, null, -1, -1);
	}

	public List<T> selectList(String hql, int pageNo, int pageSize) throws DaoException {
		return selectList(hql, null, pageNo, pageSize);
	}

	public List<T> selectList(String hql, Map<String, Object> params) throws DaoException {
		return selectList(hql, params, -1, -1);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectList(final String hql, final Map<String, Object> params, final int pageNo, final int pageSize)
			throws DaoException {
		List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setParameter(query, params);
				if (pageNo >= 0 && pageSize >= 0) {
					query.setFirstResult((pageNo - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				return query.list();
			}
		});
		return list;
	}

	protected void setParameter(Query query, Map<String, Object> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() instanceof Object[]) {
					Object[] obj = (Object[]) entry.getValue();
					query.setParameterList(entry.getKey(), obj);
				} else if (entry.getValue() instanceof Collection<?>) {
					Collection<?> collection = (Collection<?>) entry.getValue();
					query.setParameterList(entry.getKey(), collection);
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public long selectRowCount(String hql) throws DaoException {
		return selectRowCount(hql, null);
	}

	public long selectRowCount(final String hql, final Map<String, Object> params) throws DaoException {
		Long count = hibernateTemplate.execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setParameter(query, params);
				return (Long) query.uniqueResult();
			}
		});
		return count.longValue();
	}

	public T save(T entity) throws DaoException {
		hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	public PageResults<T> listPage(String hql, Map<String, Object> params, int pageNo, int pageSize)
			throws DaoException {
		String countHql = countHql(hql);
		return listPage(hql, countHql, params, pageNo, pageSize);
	}

	private String countHql(String hql) {
		String countHql = null;
		if (StringUtils.isNotBlank(hql)) {
			int fromIdx = hql.indexOf("from");
			if (fromIdx > 0) {
				countHql = hql.substring(fromIdx);
			} else {
				countHql = hql;
			}

			int orderIdx = countHql.indexOf(" order by");
			if (orderIdx > 0) {
				countHql = countHql.substring(0, orderIdx);
			}
			countHql = "select count(*) " + countHql;
		}
		return countHql;
	}

	public PageResults<T> listPage(String hql, String countHql, Map<String, Object> params, int pageNo, int pageSize)
			throws DaoException {
		List<T> entitieList = selectList(hql, params, pageNo, pageSize);
		long count = 0;
		if (StringUtils.isNotBlank(countHql)) {
			count = selectRowCount(countHql, params);
		}
		PageResults<T> queryResultsDto = new PageResults<T>(entitieList, pageNo, pageSize, count);
		return queryResultsDto;
	}

}
