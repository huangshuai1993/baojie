package com.baojie.manage.base.common.req;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class BaseReq implements Serializable {
	private static final long serialVersionUID = -7522578931569889209L;
	@ApiModelProperty("查询条件")
	public String queryStr;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("开始时间")
	public Date beginTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("结束时间")
	public Date endTime;

	@ApiModelProperty("页码默认1")
	public Integer pageNum;

	@ApiModelProperty("每页显示数量默认10")
	public Integer pageSize;

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPageNum() {
		if (null == pageNum || pageNum <= 0) {
			// 默认当前页码 1
			pageNum = new Integer(1);
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (null == pageSize) {
			// 默认每页条数 30
			pageSize = new Integer(30);
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
