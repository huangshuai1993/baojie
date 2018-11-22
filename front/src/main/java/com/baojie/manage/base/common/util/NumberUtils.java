package com.baojie.manage.base.common.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author ryokuikusa
 * @Description
 * @Create 2017年5月24日 上午10:52:02
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {


    /**
     * 格式化number 默认去掉尾数0
     *
     * @param number
     * @return
     */
    public static String formatNumber(Number number) {
        return formatNumber(number,"#,###.##");
    }

    /**
     * 格式化number,自定义pattern
     * <a href="https://blog.csdn.net/thunder4393/article/details/1739911">pattern定义</>
     *
     * @param number
     * @param pattern
     * @return
     */
    public static String formatNumber(Number number, String pattern) {
        if(number==null){
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }

    /**
     * 生成6位随机数字
     *
     * @return
     */
    public static String generateRandom() {
        return String.valueOf((Math.random() * 9 + 1) * 100000).substring(0, 6);
    }

    /**
     * 信联（生成请求交易号）
     *
     * @return
     */
    public static String generaterSerial() {
        String dateStr = DateUtil.getDateString(new Date(), "yyyyMMddss");
        Random rnd = new Random();
        int num = 10000000 + rnd.nextInt(99999999);
        String serialNo = dateStr + num;
        return serialNo;

    }

    /**
     * 两个BigDecimal进行比较
     *
     * @param v1 值1
     * @param a  比较符号
     * @param v2 值2
     * @return
     */
    public static boolean compare(BigDecimal v1, Aperator a, BigDecimal v2) {
        if (v1 == null || v2 == null || a == null) {
            throw new NullPointerException("比较方法参数存在null值");
        }
        return ArrayUtils.contains(a.getCode(), v1.compareTo(v2));
    }

    /**
     * 计算运算符
     */
    public enum Aperator {
        EQ(new int[]{0}, "等于"),
        LT(new int[]{-1}, "小于"),
        GT(new int[]{1}, "大于"),
        LT_EQ(new int[]{-1, 0}, "小于等于"),
        GT_EQ(new int[]{0, 1}, "大于等于");
        private int[] code;
        private String desc;

        Aperator(int[] code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int[] getCode() {
            return code;
        }
    }

}
