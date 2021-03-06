package com.baojie.manage.back.baojie.mapper;


import org.apache.ibatis.annotations.Param;

import com.baojie.manage.back.baojie.dao.entity.StaffEntity;
import com.baojie.manage.base.common.mapper.MyMapper;

/**
 * @author huangshuai
 * @date 2019年01月07日
 */
public interface StaffEntityMapper extends MyMapper<StaffEntity> {
	
	 Integer getAverAge(@Param("towerId")Long towerId);

}
