package com.bob.core.utils;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月4日 下午1:13:10
 */
public class UuidUtil {

    /**
     * 获取32位大写UUID
     * 
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月4日 下午1:13:28
     */
    public static String getId() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
}
