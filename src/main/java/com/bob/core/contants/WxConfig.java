package com.bob.core.contants;

/**
 * Created by Bob on 2015/12/16.
 */
public class WxConfig {

    /**
     * 微信appId
     */
    public static String appId;
    /**
     * 微信appSecret
     */
    public static String appSecret;
    /**
     * 微信partner
     */
    public static String partner;
    /**
     * 微信partnerSecret 支付商户secret
     */
    public static String partnerSecret;
    /**
     * 通知地址
     **/
    public static String notifyUrl;
    /**
     * 回调地址
     */
    public static String wxRedirectUrl;


    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        WxConfig.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        WxConfig.appSecret = appSecret;
    }

    public static String getPartner() {
        return partner;
    }

    public static void setPartner(String partner) {
        WxConfig.partner = partner;
    }

    public static String getPartnerSecret() {
        return partnerSecret;
    }

    public static void setPartnerSecret(String partnerSecret) {
        WxConfig.partnerSecret = partnerSecret;
    }

    public static String getNotifyUrl() {
        return notifyUrl;
    }

    public static void setNotifyUrl(String notifyUrl) {
        WxConfig.notifyUrl = notifyUrl;
    }

    public static String getWxRedirectUrl() {
        return wxRedirectUrl;
    }

    public static void setWxRedirectUrl(String wxRedirectUrl) {
        WxConfig.wxRedirectUrl = wxRedirectUrl;
    }
}
