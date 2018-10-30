package com.baojie.manage.base.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class Utils {
    public final static Timestamp MIN_TIMESTAMP = Timestamp.valueOf("0000-1-1 0:0:0.0");
    public final static Timestamp MAX_TIMESTAMP = Timestamp.valueOf("9999-12-31 23:59:59.999");
    public final static String dateTimeFormater = "yyyy-MM-dd HH:mm:ss";
    public final static String dateTimeFormaterNotS = "yyyy-MM-dd HH:mm";

    public final static long MINUTE = 60 * 1000l;
    public final static long HOUR = 60 * MINUTE;
    public final static long DAY = 24 * HOUR;
    public final static long WEEK = 7 * DAY;

    public static final Date getStepDate(Date date, int stepDays) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, stepDays);
        return cal.getTime();
    }

    public static Timestamp zeroDate() {
        return new Timestamp(0l);
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Long nowLong() {
        return System.currentTimeMillis();
    }

    public static Timestamp nowInSec() {
        Long currentTime = System.currentTimeMillis() / 1000l;

        return new Timestamp(currentTime * 1000l);
    }

    public static Timestamp forever() {
        return new Timestamp(253402271999000l);
    }

    public static Timestamp today() {
        Timestamp now = now();

        Date today = new Date(now.getYear(), now.getMonth(), now.getDate());

        return new Timestamp(today.getTime());
    }

    public static Timestamp offsetTimestamp(Timestamp from, long offset) {
        return new Timestamp(from.getTime() + offset);
    }

    public static long diffTimestamp(Timestamp left, Timestamp right) {
        return left.getTime() - right.getTime();
    }

    public static String randomUuid() {
        UUID uuid = UUID.randomUUID();

        String random = String.format("%016x%016x", uuid.getLeastSignificantBits(),
                uuid.getMostSignificantBits());

        return random;
    }

    public static String randomId() {
        String id = String.format("%04x", (int) (Math.random() * 65535));

        return id;
    }

    public static String validateCode() {
        String id = String.format("%04d", Math.round(Math.random() * 9999));
        return id;
    }

    public static String randomId(String prefix) {
        if (prefix == null)
            prefix = "tag";

        String id = String.format("%s-%x", prefix, (int) (Math.random() * Integer.MAX_VALUE));

        return id;
    }

    public static String randomStr() {
        StringBuilder builder = new StringBuilder();

        int count = 4;
        while (count-- != 0) {
            builder.append(randomId());
        }

        return builder.toString();
    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static Double keepScale(Double value, int scale) {
        BigDecimal big = new BigDecimal(value);

        big.setScale(scale, BigDecimal.ROUND_HALF_UP);
        value = big.doubleValue();

        return value;
    }

    public static int betweenDays(Timestamp left, Timestamp right) {
        Date leftDate = thatDay(left);
        Date rightDate = thatDay(right);

        long diff = Math.abs(leftDate.getTime() - rightDate.getTime());

        int days = (int) (diff / (1000 * 60 * 60 * 24));

        return days;
    }

    public static Timestamp thatDay(Timestamp time) {
        return new Timestamp(time.getYear(), time.getMonth(), time.getDate(), 0, 0, 0, 0);
    }

    public static Timestamp thatDayEnd(Timestamp time) {
        return new Timestamp(time.getYear(), time.getMonth(), time.getDate(), 23, 59, 59, 59);
    }

    /**
     * @param dateType
     *            yyyyMMdd(20150504) yyyyMMddHHmmss(20150504121744)
     * @param num
     *            中间随机数位数 最大19位
     * @param id
     *            相关ID（取5位，不足前补0；超出5位，截取后5位）
     * @return
     */
    public static String code(String dateType, Integer num, Integer id) {
        if (dateType == null)
            dateType = "yyyyMMddHHmmss";

        if (num == null)
            num = 4;

        SimpleDateFormat from = new SimpleDateFormat(dateType);
        String time = from.format(new Date());

        String mid = String.format("%0" + num + "d",
                (long) (Math.random() * (Math.pow(10, num) - 1)));

        String idStr = (id != null) ? String.format("%05d", id) : "";

        return time + mid + idStr;
    }

    /**
     * 生成单号 规则yyyyMMddHHmmss+6位随机数 例：20151121131315250488
     * 
     * @return
     */
    public static String num() {
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = from.format(new Date());

        long id = (long) (Math.random() * (Math.pow(10, 6) - 1));

        String idStr = String.format("%06d", id);

        return time + idStr;
    }

    public static long longOf(Object value) {
        if (value == null || !(value instanceof Number))
            return 0l;

        return ((Number) value).longValue();
    }

    public static String dumpMethod(Object... args) {
        StringBuilder builder = new StringBuilder();

        String method = Thread.currentThread().getStackTrace()[2].getMethodName();

        builder.append(method);

        builder.append("##parameter[");
        for (int index = 0; index != args.length; ++index) {
            builder.append(args[index]);

            builder.append((index % 2 == 1) ? "=" : ",");
        }

        builder.append("]");

        return builder.toString();
    }

    public static Method getCurrentMethod(Class<?> clazz) {
        try {
            StackTraceElement[] stack = new Throwable().getStackTrace();

            Method method = clazz.getMethod(stack[1].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <E> String joinStr(E[] it, String ch) {
        StringBuilder builder = new StringBuilder();

        if (it != null) {
            for (E e : it) {
                if (builder.length() != 0)
                    builder.append(ch);

                builder.append(String.valueOf(e));
            }
        }

        return builder.toString();
    }

    public static <E> String joinStr(Iterable<E> it, String ch) {
        StringBuilder builder = new StringBuilder();

        if (it != null) {
            for (E e : it) {
                if (builder.length() != 0)
                    builder.append(ch);

                builder.append(String.valueOf(e));
            }
        }

        return builder.toString();
    }

    public static <E> String joinInStr(E[] it, String ch) {
        StringBuilder builder = new StringBuilder();

        if (it != null) {
            for (E e : it) {
                if (builder.length() != 0)
                    builder.append(ch);

                builder.append("\"");
                builder.append(String.valueOf(e));
                builder.append("\"");
            }
        }

        return builder.toString();
    }

    public static <E> String joinInStr(Iterable<E> it, String ch) {
        StringBuilder builder = new StringBuilder();

        if (it != null) {
            for (E e : it) {
                if (builder.length() != 0)
                    builder.append(ch);

                builder.append("\"");
                builder.append(String.valueOf(e));
                builder.append("\"");
            }
        }

        return builder.toString();
    }

    public static <E> ArrayList<E> uniqueCollection(Collection<E> collection) {
        HashSet<E> set = new HashSet<E>(collection);

        ArrayList<E> list = new ArrayList<E>(set);

        return list;
    }

    /**
     * 隐藏字符串中间数值 （用于手机号、银行卡号、姓名等敏感信息的处理）
     * 
     * @param str
     *            待处理字符串
     * @param left
     *            左边显示位数 大于等于0
     * @param right
     *            右边显示位数 大于等于0
     * @return 已处理字符串
     */
    public static String hiddenStr(String str, Integer left, Integer right) {
        if (StringUtils.isBlank(str))
            return str;

        if (left == null || left < 0)
            left = 0;

        if (right == null || right < 0)
            right = 0;

        if ((left + right) >= str.length())
            return str;

        return str.substring(0, left)
                + Utils.joinStr(Collections.nCopies(str.length() - (left + right), "*"), "")
                + str.substring(str.length() - right);
    }

    /**
     * 隐藏字符串右侧数值 ,1*替代所有（用于姓名 例如 王大大 --王*）
     * 
     * @param str
     * @param left 左侧显示数值
     * @return
     */
    public static String hiddenRightStr(String str, Integer left) {
        if (StringUtils.isBlank(str))
            return str;
        if (left == null || left < 0)
            left = 0;
        if (left >= str.length())
            return str;
        return str.substring(0, left) + "*";
    }

    /**
     * 导出
     * 
     * @param file
     *            csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList
     *            数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSucess = false;

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 导入
     * 
     * @param file
     *            csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file) {
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }

    public static final String getDateStringByPattern(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }

    public static final Long getDateLongByPattern(String strts, String pattern) {

        if (StringUtils.isBlank(strts)) {
            return 0l;
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat(pattern);
            Date date = new Date(Long.parseLong(strts));
            String str = sf.format(date);
            return sf.parse(str).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;

    }

    public static final String getTimestampStringByPattern(String strts, String pattern) {
        if (StringUtils.isBlank(strts)) {
            return "";
        }
        Date date = new Date(Long.parseLong(strts));
        return getDateStringByPattern(date, pattern);
    }

    public static List<Long> praseListInt2Long(List<Integer> list) {
        List<Long> result = new ArrayList<Long>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer val : list) {
                if (val != null) {
                    result.add(val.longValue());
                }
            }
        }
        return result;
    }

    public static List<Integer> praseListLong2Int(List<Long> list) {
        List<Integer> result = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Long val : list) {
                if (val != null) {
                    result.add(val.intValue());
                }
            }
        }
        return result;
    }

    /**
     * 将时间戳转换转化为Timestamp
     * 
     * @param date
     * @return
     */
    public static Timestamp getTimestampByStr(String date) {
        if (date == null || date.isEmpty() || date.equals("null")) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(new Date(Long.valueOf(date)));
        Timestamp currentTime = Timestamp.valueOf(format);
        return currentTime;
    }

    public static Timestamp getTimestampByLong(Long date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date);
    }

    /**
     * 格式化金额 ---千分位分割-整数
     * 
     * @return
     */
    public static String formatAmount(Float amount) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(amount);
    }

    /**
     * Timestamp转化为string(yyyy-MM-dd)
     * 
     * @param tamp
     * @return
     */
    public static String formatShortTime(Timestamp tamp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(tamp);
    }

    /**
     * 获取下个月的第一天 2017/1/1 0:0:0
     * 
     * @return
     * @throws ParseException
     */
    public static Long getFirstMonthDay() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        String firstTime = format.format(ca.getTime());
        String firstDay = firstTime + " 00:00:00";
        SimpleDateFormat format1 = new SimpleDateFormat(dateTimeFormater);
        return format1.parse(firstDay).getTime();
    }

    /**
     * 获取距离本月最后一天的毫秒值
     * 
     * @return
     * @throws ParseException
     */
    public static Long getTimeForlastMonthDay() throws ParseException {
        Long dValue = getFirstMonthDay() - nowLong();
        return dValue;
    }

    public static String getServerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("LocalHost ip NDS fail, please check it first!");
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        System.err.println(hiddenRightStr("鹿朋", 0));
        // for (int i = 0; i < 10; i++) {
        // int x=1+(int)(Math.random()*15);
        // System.out.println(x);
        // }
    }

    // public static void main(String[] args){
    //
    //// File file = new File("/Users/zhoukeke/work/mlkx/data/data.csv");
    //// List<String> dataList=new ArrayList<String>();
    ////
    //// for (int i = 0; i < 10000; i++) {
    //// dataList.add(String.valueOf(19900000000L+i));
    //// }
    //// exportCsv(file,dataList);
    //
    //
    // File file = new File("/Users/zhoukeke/work/mlkx/data/TradingCode.csv");
    // List<String> dataList=new ArrayList<String>();
    //
    // for (int i = 1; i < 11; i++) {
    // for (int j = 1; j < 11; j++) {
    // dataList.add(i + "0NO"+j);
    // }
    //
    // }
    // exportCsv(file,dataList);
    // }
    //

}
