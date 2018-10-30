package com.baojie.manage.back.organize.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.base.common.util.CustomDateTimeDeserializer;
import com.baojie.manage.base.common.util.CustomDateTimeSerializer;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.dao.entity.StoreEntity;

public class StoreForm implements Serializable {

	private static final long serialVersionUID = 7073227100853067256L;

	private Long id;// id

	private String name;// 门店id

	private String provinceCode;// 省份编码
	private String province;// 省份名称
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date created;// 创建时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date updated;// 修改时间

	/**
	 * 状态 默认1可用
	 */
	private int status;

	public StoreForm() {
	}

	public StoreForm(StoreDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.province = SysDqHelper.lookupDQName(dto.getProvinceCode());
		this.provinceCode = dto.getProvinceCode();
		this.created = dto.getCreated();
		this.updated = dto.getUpdated();
		this.status = dto.getStatus();
	}

	public StoreForm(StoreEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.province = SysDqHelper.lookupDQName(entity.getProvinceCode());
		this.provinceCode = entity.getProvinceCode();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}
}
