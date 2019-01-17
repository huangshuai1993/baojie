package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.TowerDao;
import com.baojie.manage.back.baojie.dao.entity.TowerEntity;
import com.baojie.manage.back.baojie.mapper.TowerEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.common.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class TowerDao extends BaseDao<TowerEntity> {

	@Autowired
	private TowerEntityMapper towerEntityMapper;

	public List<TowerEntity> getTowerList(Integer pageNo, Integer pageSize, String towerName, String functionaryName,
			String beginTime, String endTime) {
		PageHelper.startPage(pageNo, pageSize);
		Example example = new Example(TowerEntity.class);
		Example.Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(functionaryName)) {
			c.andLike("functionaryName", "%"+functionaryName+"%");
		}
		if (StringUtils.isNotBlank(towerName)) {
			c.andLike("towerName", "%"+towerName+"%");
		}
		if (StringUtils.isNotBlank(beginTime)) {
			c.andGreaterThanOrEqualTo("approachTime", beginTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			c.andLessThanOrEqualTo("approachTime", endTime);
		}

		example.orderBy("updated").desc();
		return towerEntityMapper.selectByExample(example);
	}

}
