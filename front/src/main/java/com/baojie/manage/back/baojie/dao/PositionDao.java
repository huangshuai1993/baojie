package com.baojie.manage.back.baojie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.baojie.dao.PositionDao;
import com.baojie.manage.back.baojie.dao.entity.PositionEntity;
import com.baojie.manage.back.baojie.mapper.PositionEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class PositionDao extends BaseDao<PositionEntity>{

	@Autowired
	private PositionEntityMapper positionEntityMapper;

	public List<PositionEntity> getPositionListByTowerId(Long id) {
		Example example = new Example(PositionEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("towerId", id);
		example.orderBy("updated").desc();
		return positionEntityMapper.selectByExample(example);
	}

	public List<PositionEntity> getPositionList(Integer pageNumber, Integer pageSize, Long towerId) {
		PageHelper.startPage(pageNumber, pageSize);
		Example example = new Example(PositionEntity.class);
		Example.Criteria c = example.createCriteria();
		if (towerId != null) {
			c.andEqualTo("towerId", towerId);
		}
		example.orderBy("updated").desc();
		return positionEntityMapper.selectByExample(example);
	}
}
