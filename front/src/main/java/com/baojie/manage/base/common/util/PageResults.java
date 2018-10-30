package com.baojie.manage.base.common.util;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PageResults<T> implements Serializable {

	private static final long serialVersionUID = 7015204881157387554L;

	private int pageNo;
	private int pageSize;
	private long totalCount;
	private int totalPage;
	private List<T> list;

	public PageResults() {
		this(null, 0, 0, 0);
	}

	public PageResults(List<T> list, long totalCount) {
		this(list, 0, 0, totalCount);
	}

	public PageResults(List<T> list, int pageNo, int pageSize, long totalCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		if (totalCount <= 0) {
			this.totalPage = 0;
		} else {
			if (pageNo >= 0 && pageSize > 0) {
				if (totalCount % pageSize == 0) {
					this.totalPage = (int) (totalCount / pageSize);
				} else {
					this.totalPage = (int) (totalCount / pageSize) + 1;
				}
			}
		}
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
