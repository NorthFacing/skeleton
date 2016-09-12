package com.bob.core.contants;

/**
 * Created by Bob on 2015/12/16.
 */
public class BizConfig {

  public static String appDomain;

  public static long sessionTimeOut;

  public void setAppDomain(String appDomain) {
    BizConfig.appDomain = appDomain;
  }

  public void setSessionTimeOut(String sessionTimeOut) {
    Long timeOut = Long.parseLong(sessionTimeOut);
    BizConfig.sessionTimeOut = timeOut;
  }
}
