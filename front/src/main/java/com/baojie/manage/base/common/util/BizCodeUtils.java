package com.baojie.manage.base.common.util;


public class BizCodeUtils {

	/**
	 * 生成唯一业务代码(14位时间+3位部署节点+3位业务码+10位随机数)
	 * 
	 * @param nodeCode
	 * @param bizCode
	 * @return
	 */
	public static String getBizCode(String nodeCode, String bizCode) {
		StringBuffer sb = new StringBuffer("");
		// 14位时间
		sb.append(DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT));
		// 3位服务器节点编码
		if (!StringUtils.isEmpty(nodeCode)) {
			sb.append(nodeCode);
		}
		// 3位业务码
		if (!StringUtils.isEmpty(bizCode)) {
			sb.append(bizCode);
		}
		// 10位随机数
		sb.append(String.valueOf(Math.random()).substring(2, 12));

		return sb.toString();
	}
	
	
	
	/**
	 * 生成唯一业务代码(14位时间+5位业务码+10位随机数)
	 * 
	 * @param bizCode
	 * @return
	 */
	public static String getBizCode(String bizCode) {
		StringBuffer sb = new StringBuffer("");
		// 14位时间
		sb.append(DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT));
		// 5位业务码
		if (!StringUtils.isEmpty(bizCode)) {
			sb.append(bizCode);
		}
		// 10位随机数
		sb.append(String.valueOf(Math.random()).substring(2, 12));

		return sb.toString();
	}
	
}

