package com.bob.core.contants;


/**
 * 
 * 配置常量封装类
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月3日 下午3:44:54
 */
public class Config {

    /** 微信跳转访问域名 */
    public static String wxUrl;
    /** 微信appId */
    public static String appId;

    public static String getWxUrl() {
        return wxUrl;
    }

    public static void setWxUrl(String wxUrl) {
        Config.wxUrl = wxUrl;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        Config.appId = appId;
    }

}
