package com.bob.core.session;

import com.bob.core.utils.encrypt.EncoderHandler;

import java.util.Map;
import java.util.UUID;

/**
 * 用户会话对象<br>
 * 存储会话基本字段，如果需要额外存储登录用户信息，可以传入自定义用户对象：T user
 *
 * @created 2015年12月15日 下午3:24:27
 */
public class UserSession<T> {

  /**  */
  private static final long serialVersionUID = 1L;

  /**
   * 会话id
   */
  private String sessionId;
  /**
   * session会话主要字段： 用户id
   */
  private Long userId;
  /**
   * session会话主要字段： 用户名
   */
  private String loginName;
  /**
   * session会话主要字段： 账号状态(用作实现用户会话实时锁定)
   */
  private String status;
  /**
   * 用户管辖部门
   */
  private Map<String, String> userDept;
  /**
   * 用户拥有权限
   */
  private Map<String, String> userPermission;
  /**
   * 用户起始登录时间戳
   */
  private long timestamp;
  /**
   * 用户登录后主页
   */
  private String homePage;

  /**
   * 当前用户登录对象
   */
  private T loginUser;

  private String corpId;

  public UserSession() {
    super();
    this.sessionId = UserSession.generateSessionId();
    timestamp = System.currentTimeMillis();
  }

  public UserSession(Map<String, String> userPermission) {
    super();
    this.sessionId = UserSession.generateSessionId();
    this.userPermission = userPermission;
    this.timestamp = System.currentTimeMillis();
  }

  public UserSession(Map<String, String> userPermission, String homePage) {
    super();
    this.sessionId = UserSession.generateSessionId();
    this.userPermission = userPermission;
    this.timestamp = System.currentTimeMillis();
    this.homePage = homePage;
  }

  /**
   * 生成 sessionId
   *
   * @created 2015年10月12日 上午8:25:21
   */
  public static String generateSessionId() {
    return EncoderHandler.encodeByMD5(UUID.randomUUID().toString().replaceAll("-", ""));
  }

  public Map<String, String> getUserDept() {
    return userDept;
  }

  public void setUserDept(Map<String, String> userDept) {
    this.userDept = userDept;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Map<String, String> getUserPermission() {
    return userPermission;
  }

  public void setUserPermission(Map<String, String> userPermission) {
    this.userPermission = userPermission;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public T getLoginUser() {
    return loginUser;
  }

  public void setLoginUser(T loginUser) {
    this.loginUser = loginUser;
  }

  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }
}
