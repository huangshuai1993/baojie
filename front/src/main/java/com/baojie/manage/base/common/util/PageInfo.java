package com.baojie.manage.base.common.util;

import java.io.Serializable;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = -3634452114219876207L;

	public static final String PAGEINFO_CURRENTPAGE = "currentPage";
    public static final String PAGEINFO_PAGESIZE = "pageSize";
    public static final String PAGEINFO_FILTER = "filter";
    public static final String PAGEINFO_SORTER = "sorter";
    /** 默认当前页 */
    public static final Integer DEFAULT_CURRENT_PAGE = Integer.valueOf(1);
    /** 默认每页多少条记录 */
    public static final Integer DEFAULT_PAGESIZE = Integer.valueOf(10);
    
}
