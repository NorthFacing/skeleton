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


  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    WxConfig.appId = appId;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    WxConfig.appSecret = appSecret;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    WxConfig.partner = partner;
  }

  public String getPartnerSecret() {
    return partnerSecret;
  }

  public void setPartnerSecret(String partnerSecret) {
    WxConfig.partnerSecret = partnerSecret;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    WxConfig.notifyUrl = notifyUrl;
  }

  public String getWxRedirectUrl() {
    return wxRedirectUrl;
  }

  public void setWxRedirectUrl(String wxRedirectUrl) {
    WxConfig.wxRedirectUrl = wxRedirectUrl;
  }
}
