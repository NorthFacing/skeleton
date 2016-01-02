package com.bob.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 时间工具
 *
 * @author Bob
 * @created 2015年8月4日 上午11:56:49
 * @since v0.0.1
 */
public class DateUtil {

    /**
     * 当前时间，获取当前时间统一入口
     *
     * @return
     * @author Bob
     * @created 2015年8月19日 上午10:46:44
     * @since v0.0.1
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 当前时间戳
     *
     * @author Bob
     * @created 2015年8月19日 上午10:46:19
     * @since v0.0.1
     */
    public static Long getTime() {
        return getNow().getTime();
    }

    /**
     * 日期格式化
     *
     * @param date    需要格式化的时间
     * @param pattern 样式
     * @author Bob
     * @created 2015年8月19日 上午10:36:35
     * @since v0.0.1
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getNow());
        System.out.println(format(getNow(), "yyyyMMdd"));
        System.out.println(getNow().getTime());
        Random r = new Random();
        r.nextInt();
    }
}
