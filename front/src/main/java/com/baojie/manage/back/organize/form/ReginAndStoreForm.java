package com.baojie.manage.back.organize.form;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.google.common.collect.Lists;

public class ReginAndStoreForm implements Serializable {
	private static final long serialVersionUID = 2701726454783215901L;
	private String region_id;
	private String region_name;
	private List<StoreForm> storeList = Lists.newArrayList();;

	public ReginAndStoreForm() {
	}

	public ReginAndStoreForm(String region_id, String region_name) {
		this.region_id = region_id;
		this.region_name = region_name;
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

	public List<StoreForm> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreForm> storeList) {
		this.storeList = storeList;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}
}
