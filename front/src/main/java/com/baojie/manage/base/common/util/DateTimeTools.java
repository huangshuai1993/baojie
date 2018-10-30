package com.baojie.manage.base.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期时间工具类
 * <p>Title: DateTimeTools</p>
 * <p>Description: </p>
 * <p>Company: ulin360</p> 
 * @author kasim
 * @date 2014-2-2
 */
public class DateTimeTools {

    public static final String dateFormater = "yyyy-MM-dd";
    public static final String dateTimeFormater = "yyyy-MM-dd HH:mm:ss";
    public static final String dateTimeFormaterNoSec = "yyyy-MM-dd HH:mm";
    public static final String timeFormater = "HH:mm:ss";
    public static final String timeFormaterNoSec = "HH:mm";
    public static final String dateTimeFormater2 = "yyyyMMddHHmmss";
    
    private DateTimeTools() {
        
    }
    
    /**
     * 按指定格式取得当前日期字符串
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(String pattern) {
        Date date = new Date();
        return getDateStringByPattern(date, pattern);
    }
    
    /**
     * 按指定格式取得当前日期类型
     * @param pattern
     * @return
     */
    public static final Date getCurrentDate(String pattern) {
        Date date = new Date();
        String dateStr = getDateStringByPattern(date, pattern);
        return getDateByPattern(dateStr, pattern);
    }
    
    /**
     * 取得当前日期
     * @return
     */
    public static final Calendar getCurrentCalendar(){
        return Calendar.getInstance();
    }
    
    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }
    
    /**
     * 将时间戳按指定格式进行格式化
     * @param strts
     * @param pattern
     * @return
     */
    public static final String getTimestampStringByPattern(String strts, String pattern) {
        Timestamp ts = Timestamp.valueOf(strts);
        return getDateStringByPattern(ts, pattern);
    }
    
    public static final String dateFormatConvert(String strDate, String FomrPattern, String toPattern) {
        String str = "";
        if(StringUtils.isNotEmpty(strDate)){
            Date date = getDateByPattern(strDate, FomrPattern);
            str = getDateStringByPattern(date, toPattern);
        }
        return str;
    }
    
    /**
     * 将时间戳按指定格式进行格式化
     * @param ts
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(Timestamp ts, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(ts);
        return str;
    }
    
    /**
     * 将时间戳转换为日期字符串
     * @param ts
     * @return
     */
    public static final String getDateTimeString(Timestamp ts) {
        if (ts == null)
            return "";
        String str = ts.toString();
        if (str.length() >= 19)
            str = str.substring(0, 19);
        return str;
    }
    
    /**
     * 取得当前时间戳
     * @return
     */
    public static final Timestamp getDateTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
    
    /**
     * 取得当前时间戳字符串
     * @return
     */
    public static final String getDateTimeString() {
        Timestamp ts = getDateTime();
        return getDateTimeString(ts);
    }
    
    /**
     * 将日期字符串转换为Date类型
     * @param strDate
     * @param pattern
     * @return
     */
    public static final Date getDateByPattern(String strDate, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
                date = sf.parse(strDate);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return date;
    }
    
    public static final Date getStepDate(Date date, int stepDays) {
        if( date==null ){
                return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, stepDays);
        return cal.getTime();
    }
    
    public static final Date getStepSecond(Date date, int stepSeconds) {
        if( date==null ){
                return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, stepSeconds);
        return cal.getTime();
    }
    
    public static final Date getStepMonth(Date date, int stepMonths){
        if( date==null ){
                return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, stepMonths);
        return cal.getTime();
    }
    
    public static final Date getStepHour(Date date, int stepHours){
        if( date==null ){
                return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, stepHours);
        return cal.getTime();
    }
    
    public static final int getLeaveSec(long start, long end, int lenght) {
        int time = lenght - (int)((end - start)/1000);
        if(time > 0) {
                return time;
        } else {
                return 0;
        }
    }
    
    public static final int marginCalculations(Date start, Date end){
        long startTime = start.getTime();
        long endTime = end.getTime();
        int val = (int)((endTime - startTime)/1000);
        return val;
    }
    
    public static final int marginCalendarMinutes(Date start, Date end){
        long startTime = start.getTime();
        long endTime = end.getTime();
        int val = (int)((endTime - startTime)/60000);
        return val;
    }
    
    public static final Date getTodayClock(int hour){
        Date today = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(dateFormater);
        try {
            today = sf.parse(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(today.getTime() + hour * 60 * 60 *1000);
    }
    
    public static final Date getTomorrowClock(int hour){
        Date curr = getCurrentDate(dateFormater);
        Date tomorrow = getStepDate(curr, 1);
        return new Date(tomorrow.getTime() + hour * 60 * 60 *1000);
    }
    
    public static final Date getTomorrowClock(Date date, int hour){
        Date tomorrow = getStepDate(date, 1);
        return new Date(tomorrow.getTime() + hour * 60 * 60 *1000);
    }
    
    public static final int calculationDateDiff(Date start, Date end){
        long diff = (end.getTime() - start.getTime())/1000;
        long days = diff / (24*3600);
        return (int)days;
    }
    
    /**
     * 获取时间差字符串（xxx分钟之前）
     * @param start
     * @param pattern 设置返回的时间格式（如果超过一天会显示日期，这里设置这个日期格式）默认为yyyy-MM-dd
     * @return
     */
    public static final String getLevelTimeString(Date start, String pattern){
        if(pattern==null){
            pattern="yyyy-MM-dd HH:mm";
        }
        int level=marginCalculations(start, getDateTime());
        if(level/60==0){
            return level+"秒前";
        }else if(level/60<60){
            return level/60+"分钟前";
        }else if(level/3600<24){
            return level/3600+"小时前";
        }else{
            return getDateStringByPattern(start,pattern);
        }
    }
}
