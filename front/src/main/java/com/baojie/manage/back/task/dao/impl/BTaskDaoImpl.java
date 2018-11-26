package com.baojie.manage.back.task.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.task.dao.BTaskDao;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.dao.entity.TaskEntity;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

@Repository("btaskDaoImpl")
public class BTaskDaoImpl extends AbstractHibernateEntityDao<TaskEntity> implements BTaskDao {

	@Override
	public PageResults<TaskEntity> getTaskList(final String name, final String mobile, final Long workId,
			final Integer taskStatus, final Long SearchStoreId, final Integer pageNumber, final Integer pageSize)
			throws BizException {
		List<TaskEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<TaskEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<TaskEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TaskEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (workId != 0) {
					criteria.add(Restrictions.eq("empId", workId));
				}
				if (taskStatus != 10) {
					criteria.add(Restrictions.eq("taskStatus", taskStatus));
				}
				if (SearchStoreId != 0) {
					criteria.add(Restrictions.eq("storeId", SearchStoreId));
				}
				criteria.addOrder(Order.desc("created"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryTastListCount(name, mobile, workId, taskStatus, SearchStoreId);
		PageResults<TaskEntity> result = new PageResults<TaskEntity>(list, pageNumber, pageSize, count);
		return result;
	}

	public Long queryTastListCount(final String name, final String mobile, final Long workId, final Integer taskStatus,
			final Long SearchStoreId) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TaskEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (workId != 0) {
					criteria.add(Restrictions.eq("empId", workId));
				}
				if (taskStatus != 10) {
					criteria.add(Restrictions.eq("taskStatus", taskStatus));
				}
				if (SearchStoreId != 0) {
					criteria.add(Restrictions.eq("storeId", SearchStoreId));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}

	@Override
	public PageResults<TaskEntity> getTaskListByStatus(final String name, final String mobile, final Long workId,
			final Integer taskStatus, final Long SearchStoreId, final Integer pageNumber, final Integer pageSize)
			throws BizException {
		List<TaskEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<TaskEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<TaskEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TaskEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (workId != 0) {
					criteria.add(Restrictions.eq("empId", workId));
				}
				if (taskStatus != 10) {
					criteria.add(Restrictions.eq("taskStatus", taskStatus));
				}
				if (taskStatus == 10) {
					/*List<Integer> status = new ArrayList<Integer>();
					status.add(TaskStatusEnum.OK_PAY.getId());
					status.add(TaskStatusEnum.NO_PASS.getId());
					status.add(TaskStatusEnum.ING_REFUND.getId());
					status.add(TaskStatusEnum.OK_REFUND.getId());
					status.add(TaskStatusEnum.FINISH.getId());
					criteria.add(Restrictions.in("taskStatus", status));
				*/}
				if (SearchStoreId != 0) {
					criteria.add(Restrictions.eq("storeId", SearchStoreId));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.queryTastListByStatusCount(name, mobile, workId, taskStatus, SearchStoreId);
		PageResults<TaskEntity> result = new PageResults<TaskEntity>(list, pageNumber, pageSize, count);
		return result;

	}

	public Long queryTastListByStatusCount(final String name, final String mobile, final Long workId,
			final Integer taskStatus, final Long SearchStoreId) throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TaskEntity.class);
				if (StringUtils.isNotEmpty(name)) {
					criteria.add(Restrictions.eq("name", name));
				}
				if (StringUtils.isNotEmpty(mobile)) {
					criteria.add(Restrictions.eq("mobile", mobile));
				}
				if (workId != 0) {
					criteria.add(Restrictions.eq("empId", workId));
				}
				if (taskStatus != 10) {
					criteria.add(Restrictions.eq("taskStatus", taskStatus));
				}
				if (taskStatus == 10) {
					/*List<Integer> status = new ArrayList<Integer>();
					status.add(TaskStatusEnum.OK_PAY.getId());
					status.add(TaskStatusEnum.NO_PASS.getId());
					status.add(TaskStatusEnum.ING_REFUND.getId());
					status.add(TaskStatusEnum.OK_REFUND.getId());
					status.add(TaskStatusEnum.FINISH.getId());
					criteria.add(Restrictions.in("taskStatus", status));
				*/}
				if (SearchStoreId != 0) {
					criteria.add(Restrictions.eq("storeId", SearchStoreId));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}

	@Override
	public Map<Long, Long> getTaskCountByWorkId(final List<Long> ids, final List<Integer> taskStatus)
			throws BizException {
		Map<Long, Long> result = Maps.newHashMap();
		List<Map<String, Object>> list = this.getHibernateTemplate()
				.execute(new HibernateCallback<List<Map<String, Object>>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Map<String, Object>> doInHibernate(Session session)
							throws HibernateException {
						String hql = "select t.empId as id, count(*) as count from TaskEntity t where t.empId in (:ids) and t.taskStatus in (:taskStatus)  group by t.empId";
						Query query = session.createQuery(hql);
						query.setParameterList("ids", ids);
						query.setParameterList("taskStatus", taskStatus);
						query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
						return query.list();
					}
				});
		// 转化map
		for (Map<String, Object> map : list) {
			result.put((Long) map.get("id"), (Long) map.get("count"));
		}
		return result;
	}

	@Override
	public List<TaskEntity> findTaskListByEmpId(Long empId, List<Integer> taskStatus) throws BizException {
		String hql = "from TaskEntity t where  t.empId = :empId and t.taskStatus in (:taskStatus)";
		Map<String, Object> params = Maps.newHashMap();
		params.put("empId", empId);
		params.put("taskStatus", taskStatus);
		List<TaskEntity> taskList = selectList(hql, params);
		return taskList;
	}

}
