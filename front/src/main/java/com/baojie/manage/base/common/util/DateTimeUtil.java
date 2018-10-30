package com.baojie.manage.base.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 获取当前时间为基点的时间  -1  一天前
	 * @param num
	 * @return
	 */
	public static Long getOtherDay(int num) {
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.DAY_OF_MONTH, num);  
        return calendar.getTimeInMillis();
	}
	/**
	 * 得到某天后的0点
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static Long getOtherDayZero(int num) {
	    Long time = null ;
	    try {
    	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            Calendar ca = Calendar.getInstance();   
            ca.setTime(new Date());  
            ca.add(Calendar.DAY_OF_MONTH, num);  
            String firstTime = format.format(ca.getTime());
            String firstDay=firstTime + " 00:00:00";
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = format1.parse(firstDay).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            time =null;
        }
	    return time;
    }
	/**
	* 日期转换成字符串 yyyy-MM-dd
	* @param date
	* @return str
	*/
	public static String DateToStr(Date date) {
	  // HH:mm:ss
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String str = format.format(date);
	   return str;
	}
	/**
	 *  日期转换成字符串 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String DateToStrSS(Date date) {
      // HH:mm:ss
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String str = format.format(date);
       return str;
    }
   
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	public static void main(String[] args) {
	    SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    Date d = new Date(1510886962000l);
	    String  a = sdf.format(d);
	    System.err.println(a);
    }
}
