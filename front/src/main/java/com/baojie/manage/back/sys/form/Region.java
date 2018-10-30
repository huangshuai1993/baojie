package com.baojie.manage.back.sys.form;

import com.baojie.manage.base.common.util.SysDqHelper.Dq;

public class Region {
	private String region_id;

	private String region_name;

	public Region() {
	}

	public Region(Dq dq) {
		this.region_id = dq.code;
		this.region_name = dq.chName;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
}
