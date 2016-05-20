package com.bob.core.utils.javaUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 时间工具
 *
 * @author Bob
 * @created 2015年8月4日 上午11:56:49
 * @since v0.1
 */
public class DateUtil {

    /**
     * 当前时间，获取当前时间统一入口
     *
     * @return
     * @author Bob
     * @created 2015年8月19日 上午10:46:44
     * @since v0.1
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 当前时间戳
     *
     * @author Bob
     * @created 2015年8月19日 上午10:46:19
     * @since v0.1
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
     * @since v0.1
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    /**
     * 根据日期获取周几
     *
     * @param date 日期
     * @return 周几：1-6代表周一到周六；7，代表周日
     */
    public static int getWeekNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 原生函数是从周日算作每周的第一天,所以需要转换
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 0 ? 7 : week;
    }

    public static void main(String[] args) {
        System.out.println(getNow());
        System.out.println(format(getNow(), "yyyyMMdd"));
        System.out.println(getNow().getTime());
        System.out.println(getWeekNum(getNow()));
        Random r = new Random();
        r.nextInt();
    }
}
