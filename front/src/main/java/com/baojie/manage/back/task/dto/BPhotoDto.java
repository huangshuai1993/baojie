package com.baojie.manage.back.task.dto;

import com.baojie.manage.base.service.IDto;

public class BPhotoDto implements IDto {
	private static final long serialVersionUID = 2276368836337065712L;
	private Long id;
	private String name;
	private int type;
	private double lat;
	private double lng;
	private String origUrl;// 原始照片
	private String lowUrl;// 低质量照片
	private String smallUrl;// 缩略小图照片

	public BPhotoDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getOrigUrl() {
		return origUrl;
	}

	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public String getLowUrl() {
		return lowUrl;
	}

	public void setLowUrl(String lowUrl) {
		this.lowUrl = lowUrl;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

}
