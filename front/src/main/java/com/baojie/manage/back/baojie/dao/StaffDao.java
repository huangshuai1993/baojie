package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.StaffDao;
import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.back.baojie.mapper.StaffEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class StaffDao extends BaseDao<StaffEntity>{

	@Autowired
	private StaffEntityMapper staffEntityMapper;

	public List<StaffEntity> getStaffList(Integer pageNo, Integer pageSize, Long towerId, String staffName,Integer status,Integer startAge,Integer endAge,Integer gender) {
		PageHelper.startPage(pageNo, pageSize);
		Example example = new Example(StaffEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(staffName)) {
			c.andEqualTo("name", staffName);
		}
		if (towerId != null) {
			c.andEqualTo("towerId", towerId);
		}
		if(status != null){
			c.andEqualTo("status", status);
		}
		if(startAge != null){
			c.andGreaterThanOrEqualTo("age", startAge);
		}
		if(endAge != null){
			c.andLessThanOrEqualTo("age", endAge);
		}
		if(gender != null){
			c.andEqualTo("gender", gender);
		}
		example.orderBy("updated").desc();
		return staffEntityMapper.selectByExample(example);
	}
	public List<StaffEntity> getStaffByTowerId(Long id){
		Example example = new Example(StaffEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("towerId", id);
		return staffEntityMapper.selectByExample(example);
	}
	/**
	 * 查询平均年龄
	 * @param towerId
	 * @return
	 */
	public Integer getAverAge(Long towerId){
		return staffEntityMapper.getAverAge(towerId);
	}
}
