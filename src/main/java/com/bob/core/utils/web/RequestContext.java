package com.bob.core.utils.web;

import com.bob.core.utils.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * request 全局请求会话<br>
 * 用户存储用户相关信息<br>
 * 此类可以在velocity的工具类中直接调用
 *
 * @created 2015年10月24日 下午10:14:58
 */
@SuppressWarnings("all")
public final class RequestContext {

  private final static String LOGIN_USER_KEY = "login.user";
  private final static String LOGIN_USER_ID_KEY = "login.user.id";
  private final static String LOGIN_USER_NAME_KEY = "login.user.name";
  private final static String HTTP_REQUEST_KEY = "http.request";
  private final static String HTTP_RESPONSE_KEY = "http.response";

  private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

  private static Map getThreadLocalMap() {
    Map map = (Map) threadLocal.get();
    if (map == null) {
      map = new HashMap();
    }
    return map;
  }

  public static Long getLoginUserId() {
    Object object = getThreadLocalMap().get(RequestContext.LOGIN_USER_ID_KEY);
    if (object == null)
      return null;

    return Long.valueOf(object.toString());
  }

  public static void setLoginUserId(Long id) {
    Map map = getThreadLocalMap();
    map.put(RequestContext.LOGIN_USER_ID_KEY, id);
    threadLocal.set(map);
  }

  public static UserSession getLoginUser() {
    return (UserSession) getThreadLocalMap().get(RequestContext.LOGIN_USER_KEY);
  }

  public static void setLoginUser(Object user) {
    Map map = getThreadLocalMap();
    map.put(RequestContext.LOGIN_USER_KEY, user);
    threadLocal.set(map);
  }

  public static String getLoginUserName() {
    return (String) getThreadLocalMap().get(RequestContext.LOGIN_USER_NAME_KEY);
  }

  public static void setLoginUserName(String username) {
    Map map = getThreadLocalMap();
    map.put(RequestContext.LOGIN_USER_NAME_KEY, username);
    threadLocal.set(map);
  }

  public static HttpServletRequest getRequest() {
    return (HttpServletRequest) getThreadLocalMap().get(RequestContext.HTTP_REQUEST_KEY);
  }

  public static void setRequest(HttpServletRequest request) {
    Map map = getThreadLocalMap();
    map.put(RequestContext.HTTP_REQUEST_KEY, request);
    threadLocal.set(map);
  }

  public static HttpServletResponse getResponse() {
    return (HttpServletResponse) getThreadLocalMap().get(RequestContext.HTTP_RESPONSE_KEY);
  }

  public static void setResponse(HttpServletResponse response) {
    Map map = getThreadLocalMap();
    map.put(RequestContext.HTTP_RESPONSE_KEY, response);
    threadLocal.set(map);
  }

  public static void clearVariable() {
    threadLocal.remove();
  }
}
