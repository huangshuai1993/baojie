package com.baojie.manage.back.baojie.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.SalaryDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.dao.entity.SalaryEntity;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.dao.AbstractHibernateEntityDao;
import com.baojie.manage.base.exception.BizException;

@Repository("com.baojie.manage.back.baojie.dao.impl.SalaryDaoImpl")
public class SalaryDaoImpl extends AbstractHibernateEntityDao<SalaryEntity> implements SalaryDao {

	@Override
	public PageResults<SalaryEntity> getAllSalary(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time) throws BizException {
		List<SalaryEntity> list = this.getHibernateTemplate().execute(new HibernateCallback<List<SalaryEntity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<SalaryEntity> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(SalaryEntity.class);
				if (towerId != null) {
					criteria.add(Restrictions.eq("towerId", towerId));
				}
				if (StringUtils.isNotEmpty(time)) {
					criteria.add(Restrictions.eq("salaryMonth",time));
				}
				if (StringUtils.isNotEmpty(searchName)) {
					criteria.add(Restrictions.like("staffName", "%" + searchName + "%"));
				}
				criteria.addOrder(Order.desc("updated"));
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				return criteria.list();
			}
		});
		Long count = this.querySalaryCount(pageNumber, pageSize, towerId, searchName,time);
		PageResults<SalaryEntity> results = new PageResults<SalaryEntity>(list, pageNumber, pageSize, count);
		return results;
	}

	public Long querySalaryCount(Integer pageNumber, Integer pageSize, Long towerId,
			String searchName,String time)
			throws BizException {
		Long count = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(SalaryEntity.class);
				if (towerId != null) {
					criteria.add(Restrictions.eq("towerId", towerId));
				}
				if (StringUtils.isNotEmpty(time)) {
					criteria.add(Restrictions.eq("salaryMonth",time));
				}
				if (StringUtils.isNotEmpty(searchName)) {
					criteria.add(Restrictions.like("staffName", "%" + searchName + "%"));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
		});
		return count == null ? 0 : count;
	}

	@Override
	public long queryCountSalaryByMonth(String time) throws BizException {
		Map<String,Object> map = new HashMap<>();
		String hql  = "from SalaryEntity e where e.salaryMonth=:salaryMonth";
		map.put("salaryMonth", time);
		return selectRowCount(hql,map);
	}
}
