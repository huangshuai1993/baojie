package com.baojie.manage.base.common.util;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Description 时间工具类 
 * @author ryokuikusa
 * @Create 2017年4月26日 下午1:18:45
 */
public class DateUtil {
	/** */
	public static final String DATESHOWFORMAT = "yyyy-MM-dd";

	/** */
	public static final String TIMESHOWFORMAT = "HH:mm:ss";
	/** */
	public static final String DATETIMESHOWFORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 14位默认时间格式 **/
	public static final String TIME_STR_FORMAT = "yyyyMMddHHmmss";

	public static final Long MINUTES_TEN = 600000L;

	/**
	 * 时间格式（yyyy-MM-dd 00:00:00），如：2015-07-16 ：00:00:00
	 */
	public static final String DATETIMESHOWFORMAT2 = "yyyy-MM-dd 00:00:00";
	
	/**
	 * 时间格式（yyyy-MM-dd 23:59:59），如：2015-07-16 ：23:59:59
	 */
	public static final String DATETIMESHOWFORMAT3 = "yyyy-MM-dd 23:59:59";

	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long)
	 */
	public static long getBetweenDays(String startDate, String endDate) {
		if (endDate == null || startDate == null) {
			return -1;
		}
		Date dateStart = isDate(startDate);
		if (null == dateStart) {
			return -1;
		}
		Date dateEnd = isDate(endDate);
		if (null == dateEnd) {
			return -1;
		}
		return getBetweenDays(dateStart, dateEnd);
	}

	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long)
	 */
	public static Long getBetweenDays(Date startDate, Date endDate) {
		if (endDate == null || startDate == null) {
			return -1L;
		}
		Long days = endDate.getTime() - startDate.getTime();
		days = days / (1000 * 60 * 60 * 24);
		return days;
	}

	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(String baseDate, int dayCount, String patternString) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		if (DATETIMESHOWFORMAT.equals(patternString)) {
			int hour = Integer.parseInt(baseDate.substring(11, 13));
			int minute = Integer.parseInt(baseDate.substring(14, 16));
			int second = Integer.parseInt(baseDate.substring(17, 19));
			calendar.set(year, month - 1, date, hour, minute, second);
		} else {
			calendar.set(year, month - 1, date);
		}
		calendar.set(year, month - 1, date);
		calendar.add(Calendar.DATE, dayCount);
		Date date2 = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String dateString = formatter.format(date2);
		return dateString;
	}

	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(Date baseDate, int dayCount, String patternString) {
		String baseDate2 = getDateString(baseDate, DATETIMESHOWFORMAT);
		return getAfterDate(baseDate2, dayCount, patternString);
	}

	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(String baseDate, int monthCount, String patternString) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		if (DATETIMESHOWFORMAT.equals(patternString)) {
			int hour = Integer.parseInt(baseDate.substring(11, 13));
			int minute = Integer.parseInt(baseDate.substring(14, 16));
			int second = Integer.parseInt(baseDate.substring(17, 19));
			calendar.set(year, month - 1, date, hour, minute, second);
		} else {
			calendar.set(year, month - 1, date);
		}
		calendar.add(Calendar.MONTH, monthCount);
		Date date2 = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String dateString = formatter.format(date2);
		return dateString;
	}

	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(Date baseDate, int monthCount, String patternString) {
		String baseDate2 = getDateString(baseDate, DATETIMESHOWFORMAT);
		return getAfterMonth(baseDate2, monthCount, patternString);
	}

	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(String baseDate, int monthCount, int dateCount, String patternString) {
		int day = Integer.parseInt(baseDate.substring(8, 10));
		String endDate = getAfterMonth(baseDate, monthCount, patternString);
		int endDay = Integer.parseInt(endDate.substring(8, 10));
		// 说明日期没变
		if (endDay == day) {
			// 月数为正则为减一
			if (monthCount > 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			} else {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		} else { // 日期已变
			if (monthCount < 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		}
		return endDate;
	}

	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(Date baseDate, int monthCount, int dateCount, String patternString) {
		String baseDate2 = getDateString(baseDate, DATETIMESHOWFORMAT);
		return getEndDate(baseDate2, monthCount, dateCount, patternString);
	}

	/**
	 * 当前日期转换为指定月数后 的日期
	 * 
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 转换后的日期
	 */
	public static String getBeforeMonth(int monthCount, String patternString) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthCount);
		Date date2 = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		return formatter.format(date2);
	}

	/**
	 * 日期格式化(String转换为Date)
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date getDateToString(String dateStr, String patten) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化(String转换为String)
	 * 
	 * @param date
	 *            日期字符串
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getDateString(String date, String patternString) { 
		if (date == null) {
		    return "";
		}
		int dateLength = 10;
		if (date.length() < dateLength) {
		    return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.ENGLISH);
		int len = patternString.length();
		if (len > date.length()) {
			patternString = patternString.substring(0, date.length());
		}
		return formatter.format(getDateToString(date, patternString));
	}

	/**
	 * 日期格式化(Date转换为String)
	 * 
	 * @param date2
	 *            日期
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getDateString(Date date2, String patternString) {
		String dateString = "";
		if (date2 != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(patternString);
			dateString = formatter.format(date2);
		}
		return dateString;
	}

	/**
	 * 日期格式转换 DATE to DATE
	 * 
	 * @param date2
	 *            日期
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date dateToDate(Date date2, String patten) {
		String dateStr = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			if (date2 != null) {
				dateStr = formatter.format(date2);
			}
			return formatter.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获得格式化日期之后的 String数据
	 * 
	 * @param dateLong
	 * @param patten
	 * @return
	 */
	public static String getDateOfString(Long dateLong, String patten) {
		if (dateLong != null) {
			return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
		}
		return "";
	}

	/**
	 * 文本时间转换为时间对象
	 * 
	 * @param baseDate
	 *            日期字符串
	 * @return
	 */
	public static java.sql.Date getSqlDate(String baseDate) {
		if (baseDate == null || baseDate.length() == 0) {
		    return null;
		}
		Date date = getDateToString(baseDate, DATESHOWFORMAT);
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.util.Date对象转换为java.sql.Date对象
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return Date java.sql.Date对象
	 */
	public static java.sql.Date utilDateToSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为int型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(int year, int month, int date, String patternString) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		Date showDate = calendar.getTime();
		dateString = formatter.format(showDate);
		return dateString;
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为String型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(String year, String month, String date, String patternString) {
		String dateString = "";
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(date);
			dateString = getDateString(y, m, d, patternString);
		} catch (Exception e) {
		}
		return dateString;
	}

	/**
	 * 获取当前日期
	 * 
	 * @param patten
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateStr(String patternString) {
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String date = formatter.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date isDate(String dateStr) {
		String dateFormat1 = "yyyy/MM/dd";
		String dateFormat2 = "yyyy-MM-dd";
		String dateFormat3 = "yyyyMMdd";
		String dateFormat4 = "yyyy.MM.dd";
		String[] dateFormat = { dateFormat1, dateFormat2, dateFormat3, dateFormat4 };
		for (int i = 0; i < dateFormat.length; i++) {
			Date tempDate = isDate(dateStr, dateFormat[i]);
			if (null != tempDate) {
				return tempDate;
			}
		}
		return null;
	}

	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date isDate(String dateStr, String patternString) {
		if (StringUtils.isBlank(patternString)) {
			patternString = DATESHOWFORMAT;
		}
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
			formatDate.setLenient(false);
			ParsePosition pos = new java.text.ParsePosition(0);
			Date tempDate = formatDate.parse(dateStr, pos);
			tempDate.getTime();
			return tempDate;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把Date转换为Calendar对象
	 * 
	 * @param d
	 *            Date对象
	 * @return Calendar对象
	 */
	public static Calendar getCalendar(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}

	/**
	 * 将时间对象转换成指定的格式有小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATETIMESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 将时间对象转换成指定的格式有
	 * 
	 * @param date
	 * @return
	 */
	public static String parsTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(TIMESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 将时间对象转换成指定的格式无小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static String firstDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		return getDateString(firstDate, DATESHOWFORMAT);
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static String lastDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return getDateString(lastDate, DATESHOWFORMAT);
	}

	/**
	 * 获取当前数据里的时间参数
	 * 
	 * @return
	 */
	public static String getDateStr() {
		return "sysdate";
	}

	/**
	 * 获取上一个月的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 获取日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取日期事第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取上一个月的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 获取日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取日期的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtil.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取日期的第几小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取日期的第几分钟
	 * 
	 * @param date
	 * @return
	 */
	public static int getMin(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.MINUTE);
	}

	/**
	 * 检测d1 是否大于等于d2
	 * 
	 * @param d1
	 * @param d2
	 * @return true d1 是否大于等于d2
	 */
	public static boolean checkMax(Date d1, Date d2) {
		boolean flag = false;
		if (null != d1) {
			if (null != d2) {
				String d1s = getDateString(d1, "yyyyMMdd");
				String d12s = getDateString(d2, "yyyyMMdd");
				if (Double.valueOf(d1s) >= Double.valueOf(d12s)) {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		return flag;
	}

	public static boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK) || Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return true;
		}
		return false;
	}

	/**
	 * 给定时间往后延给定分钟
	 * 
	 * @param date
	 * @param minute
	 * @return
	 * @author doumingjun
	 * @create date 2012-06-27
	 */
	public static Date addMinutes(Date date, int minute) {
		if (null == date) {
		    date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return (Date) calendar.getTime();
	}

	public static Date parseDate(String date, String format) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat(format);
		return parser.parse(date);
	}

	public static String parseDateToString(Date date, String format) throws ParseException {
		if (null == date) {
			return null;
		}
		SimpleDateFormat parser = new SimpleDateFormat(format);
		return parser.format(date);
	}
	
	/**
	 * 获得当前日期
	 * @param fmt
	 * @return
	 */
	public static String getCurrentDate(String fmt){
		if (StringUtils.isEmpty(fmt)) {
			fmt = DATESHOWFORMAT;
		}
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		// 为获取当前系统时间
		return df.format(new Date());
	}

	
	/**
     * 日期相减，返回相差的秒数
     *
     * @return long
     */
    public static long diffDateSecond(Date date, Date date1) {
        int unit = 1000;
        return diffDate(date, date1, unit);
    }
    
    /**
     * 返回两个日期返回的天数
     * @param date
     * @param date1
     * @return
     */
    public static long diffDateDays(Date date, Date date1) {
    	int unit = 1000*3600*24;
    	return diffDate(date, date1, unit);
    }

    /**
     * 返回毫秒
     * @param date
     * @param date1
     * @param unit
     * @return
     */
    private static long diffDate(Date date, Date date1, int unit) {
        if (date.after(date1)) {
            return (getMillis(date) - getMillis(date1)) / unit;
        } else {
            return (getMillis(date1) - getMillis(date)) / unit;
        }
    }
    
    /**
     * 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }
    
    /**
   	 * 将当期时间格式转成yyyyMMddHHmmss
   	 * @param date
   	 * @return
   	 */
   	public static String formateDate(Date date) {
   		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
   		String format = df.format(date);
   		return format;
   	}

	/**
	 * 格式化时间
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date,String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String format = df.format(date);
		return format;
	}

   	
   	/**
   	 * 格式化日期成yyyy-MM-dd HH:mm:ss格式
   	 * @param date
   	 * @return
   	 */
   	public static String formateDate2(Date date) {
   		SimpleDateFormat df = new SimpleDateFormat(DATETIMESHOWFORMAT);
   		String format = df.format(date);
   		return format;
   	}
   	
   	/**
     * 获取当前时间到夜里24点还有多少秒
     *
     * @category @author shengguofan@hujiang.com
     * @since 2017/9/19 11:14
     */
    public static int getNowSecondsByToday() {
        Date nowDate = new Date();
        String toDateStr = getTime(nowDate, DATESHOWFORMAT) + " 23:59:59";
        Date toDate = getDate(toDateStr, DATETIMESHOWFORMAT);
        long seconds = diffDateSecond(nowDate, toDate);
        return (int) seconds;
    }
  
    /**
     * 格式化时间
     */
    public static String getTime(Date date, String dateStyle) {
        if (null == date || null == dateStyle) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
        return sdf.format(date);
    }
    
    /**
     * 将时间字符串转换为时间类型
     *
     * @param dateStr 时间字符串
     * @param dateStyle 时间格式
     * @return 时间对象
     */
    public static Date getDate(String dateStr, String dateStyle) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(dateStyle)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return date;
    }


	/**
	 * 日期添加指定天数后得时间
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDaysStr(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return calendar.getTime();
	}
	
	/**
     * 获取时间当天的开始时间
     * @param date
     * @return
     */
    public static final Date getDayDateStart(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取时间当天的结束时间
     * @param date
     * @return
     */
    public static final Date getDayDateEnd(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }
}

