package com.bob.core.utils.shiro.session;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.shiro.session.mgt.SimpleSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ShiroSession extends SimpleSession {

  Logger logger = LoggerFactory.getLogger(ShiroSession.class);

  private String userAgent;

  public ShiroSession() {
    super();
  }

  @Override
  public void setLastAccessTime(Date lastAccessTime) {
    if (getLastAccessTime() != null) {
      long last = getLastAccessTime().getTime();
      long now = lastAccessTime.getTime();
      //如果5s内访问，则不更新session，防止频繁保存
      if ((last - now) >= 5000) {
        super.setLastAccessTime(lastAccessTime);
      } else {
        logger.debug("ShiroSession ignore lastAccessTime change");
      }
    }
  }

  @Override
  public void setAttribute(Object key, Object value) {
    Object obj = this.getAttribute(key);
    // 防止过于频繁的保存
    if (obj != null && obj.equals(value)) {
      logger.debug("ShiroSession ignore attribute update");
      return;
    }
    super.setAttribute(key, value);
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
