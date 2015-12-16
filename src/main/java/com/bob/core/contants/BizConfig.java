package com.bob.core.contants;

import java.util.Properties;

/**
 * Created by Bob on 2015/12/16.
 */
public class BizConfig {

    public static String appDomain;

    public static String getAppDomain() {
        return appDomain;
    }

    public static void setAppDomain(String appDomain) {
        BizConfig.appDomain = appDomain;
    }
}
