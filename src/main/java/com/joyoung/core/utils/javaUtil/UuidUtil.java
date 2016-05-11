package com.joyoung.core.utils.javaUtil;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author Bob
 * @created 2015年7月4日 下午1:13:10
 * @since v0.1
 */
public class UuidUtil {

    /**
     * 获取32位大写UUID
     *
     * @author Bob
     * @created 2015年7月4日 下午1:13:28
     * @since v0.1
     */
    public static String getId() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
}
