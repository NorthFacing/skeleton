package com.bob.core.utils;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    /***
     * 是否是ajax请求的判断
     *
     * @author Bob
     * @created 2015年7月24日 下午2:55:21
     * @since v0.1
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader(
                "X-Requested-With").toString()));
    }

}
