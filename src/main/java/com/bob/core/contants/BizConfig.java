package com.bob.core.contants;

/**
 * Created by Bob on 2015/12/16.
 */
public class BizConfig {

  public static String appDomain;

  public static int sessionTimeOut;

  public void setAppDomain(String appDomain) {
    BizConfig.appDomain = appDomain;
  }

  public void setSessionTimeOut(String sessionTimeOut) {
    System.out.println(sessionTimeOut);
    Integer timeOut = Integer.parseInt(sessionTimeOut);
    BizConfig.sessionTimeOut = timeOut;
  }
}
