package com.joyoung.core.utils;

import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Bob on 2015/10/13.
 */

public class ApplicationContextUtil {
    public static WebApplicationContext webApplicationContext;

    public static WebApplicationContext getContext() {
        return webApplicationContext;
    }

    public static void setContext(WebApplicationContext context) {
        webApplicationContext = context;
    }
}

