package com.bob.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTool {

    /**
     * 日期格式化，手动指定格式化样式.
     * 
     * @param formatter 格式化形式，比如：yyyy-MM-dd HH:mm:ss
     * @param d 需要格式化的日期
     * @return 格式化之后的日期
     * @author Bob
     * @created 2015年6月24日 下午6:34:53
     */
    public static String format(String formatter, LocalDateTime d) {
        return DateTimeFormatter.ofPattern(formatter).format(d);
    }

    /**
     * 格式化为 yyyy-MM-dd HH:mm:ss 样式.
     * 
     * @param d 需要格式化的日期
     * @return 格式化之后的日期
     * @author Bob
     * @created 2015年6月24日 下午6:33:22
     */
    public static String format(LocalDateTime d) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(d);
    }

    /**
     * 将 LocalDateTime 转换为 Date .
     * 
     * @param d 需要转换的日期
     * @return 转换后的日期
     * @author Bob
     * @created 2015年6月24日 下午6:36:22
     */
    public static Date toDate(LocalDateTime d) {
        Instant instant = d.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
