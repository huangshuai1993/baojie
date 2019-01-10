package com.baojie.manage.back.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baojie.manage.back.sys.dao.entity.PowerEntity;
import com.baojie.manage.back.sys.mapper.PowerEntityMapper;
import com.baojie.manage.base.common.service.BaseDao;
import com.baojie.manage.base.exception.BizException;

import tk.mybatis.mapper.entity.Example;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
@Repository
public class PowerEntityDao extends BaseDao<PowerEntity> {

	@Autowired
	private PowerEntityMapper powerEntityMapper;

	public List<PowerEntity> getPowerLevelTwo() {
		Example example = new Example(PowerEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andIsNotNull("parentId");
		return powerEntityMapper.selectByExample(example);
	}

	public List<PowerEntity> getPowerByPowerId(List<Long> powerId) {
		Example example = new Example(PowerEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andIn("powerId", powerId);
		return powerEntityMapper.selectByExample(example);
	}

	public List<PowerEntity> getLevelOne() throws BizException {
		Example example = new Example(PowerEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andIsNull("parentId");
		return powerEntityMapper.selectByExample(example);
	}

	public PowerEntity getPowerByParentId(Long parentId) throws BizException {
		Example example = new Example(PowerEntity.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("parentId", parentId);
		List<PowerEntity> selectByExample = powerEntityMapper.selectByExample(example);
		PowerEntity pow = null;
		if (selectByExample != null && !selectByExample.isEmpty()) {
			pow = selectByExample.get(0);
		}
		return pow;
	}
}
