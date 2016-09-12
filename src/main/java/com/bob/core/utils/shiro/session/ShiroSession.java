//package com.bob.core.utils.shiro.session;
//
//import org.apache.commons.lang.builder.ReflectionToStringBuilder;
//import org.apache.shiro.session.mgt.SimpleSession;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Map;
//
///**
// * 由于SimpleSession lastAccessTime更改后也会调用SessionDao update方法，
// * 增加标识位，如果只是更新lastAccessTime字段，SessionDao的update方法直接返回
// * TODO: 好像并没有实现上面描述的效果，继续进行验证吧
// */
//public class ShiroSession extends SimpleSession {
//
//  // 除lastAccessTime以外其他字段发生改变时为true
//  private boolean isChanged;
//
//  public ShiroSession() {
//    super();
//    this.setChanged(true);
//  }
//
//  public ShiroSession(String host) {
//    super(host);
//    this.setChanged(true);
//  }
//
//
//  @Override
//  public void setId(Serializable id) {
//    super.setId(id);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setStopTimestamp(Date stopTimestamp) {
//    super.setStopTimestamp(stopTimestamp);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setExpired(boolean expired) {
//    super.setExpired(expired);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setTimeout(long timeout) {
//    super.setTimeout(timeout);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setHost(String host) {
//    super.setHost(host);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setAttributes(Map<Object, Object> attributes) {
//    super.setAttributes(attributes);
//    this.setChanged(true);
//  }
//
//  @Override
//  public void setLastAccessTime(Date lastAccessTime) {
//    if (getLastAccessTime() != null) {
//      long last = getLastAccessTime().getTime();
//      long now = lastAccessTime.getTime();
//      //如果5s内访问 则不更新session，否则需要更新远端过期时间
//      if ((last - now) >= 5000) {
//        //发送通知
//      }
//    }
//    super.setLastAccessTime(lastAccessTime);
//  }
//
//  /**
//   * 防止过于频繁的保存
//   */
//  @Override
//  public void setAttribute(Object key, Object value) {
//    Object obj = this.getAttribute(key);
//    if (obj != null && obj.equals(value)) {
//      return;
//    }
//    super.setAttribute(key, value);
//    this.setChanged(true);
//  }
//
//  @Override
//  public Object removeAttribute(Object key) {
//    this.setChanged(true);
//    return super.removeAttribute(key);
//  }
//
//  /**
//   * 停止
//   */
//  @Override
//  public void stop() {
//    super.stop();
//    this.setChanged(true);
//  }
//
//  /**
//   * 设置过期
//   */
//  @Override
//  protected void expire() {
//    this.stop();
//    this.setExpired(true);
//  }
//
//  public boolean isChanged() {
//    return isChanged;
//  }
//
//  public void setChanged(boolean isChanged) {
//    this.isChanged = isChanged;
//  }
//
//
//  @Override
//  public boolean equals(Object obj) {
//    return super.equals(obj);
//  }
//
//  @Override
//  protected boolean onEquals(SimpleSession ss) {
//    return super.onEquals(ss);
//  }
//
//  @Override
//  public int hashCode() {
//    return super.hashCode();
//  }
//
//  @Override
//  public String toString() {
//    return ReflectionToStringBuilder.toString(this);
//  }
//
//}
