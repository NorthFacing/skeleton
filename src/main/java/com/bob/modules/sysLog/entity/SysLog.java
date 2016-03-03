package com.bob.modules.sysLog.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.time.LocalDateTime;

/**
 * SysLog
 *
 * @author Bob
 * @Date 2016-3-3 9:27:06
 * @since v0.1
 */
public class SysLog extends BaseEntity {

  private String corpId;    //  医院ID
  private long userId;    //  用户ID
  private String userName;    //  用户姓名
  private String userIp;    //  IP
  private String dataId;    //  数据ID
  private String oprName;    //  操作名称
  private String oprType;    //  操作类型
  private LocalDateTime oprTime;    //  操作时间
  private String module;    //  模块
  private String url;    //  URL地址
  private String inParams;    //  入参
  private String outParams;    //  出参


  /**
   * 医院ID
   */
  public String getCorpId() {
    return corpId;
  }

  public SysLog setCorpId(String corpId) {
    this.corpId = corpId;
    return this;
  }


  /**
   * 用户ID
   */
  public long getUserId() {
    return userId;
  }

  public SysLog setUserId(long userId) {
    this.userId = userId;
    return this;
  }


  /**
   * 用户姓名
   */
  public String getUserName() {
    return userName;
  }

  public SysLog setUserName(String userName) {
    this.userName = userName;
    return this;
  }


  /**
   * IP
   */
  public String getUserIp() {
    return userIp;
  }

  public SysLog setUserIp(String userIp) {
    this.userIp = userIp;
    return this;
  }


  /**
   * 数据ID
   */
  public String getDataId() {
    return dataId;
  }

  public SysLog setDataId(String dataId) {
    this.dataId = dataId;
    return this;
  }


  /**
   * 操作名称
   */
  public String getOprName() {
    return oprName;
  }

  public SysLog setOprName(String oprName) {
    this.oprName = oprName;
    return this;
  }


  /**
   * 操作类型
   */
  public String getOprType() {
    return oprType;
  }

  public SysLog setOprType(String oprType) {
    this.oprType = oprType;
    return this;
  }


  /**
   * 操作时间
   */
  public LocalDateTime getOprTime() {
    return oprTime;
  }

  public SysLog setOprTime(LocalDateTime oprTime) {
    this.oprTime = oprTime;
    return this;
  }


  /**
   * 模块
   */
  public String getModule() {
    return module;
  }

  public SysLog setModule(String module) {
    this.module = module;
    return this;
  }


  /**
   * URL地址
   */
  public String getUrl() {
    return url;
  }

  public SysLog setUrl(String url) {
    this.url = url;
    return this;
  }


  /**
   * 入参
   */
  public String getInParams() {
    return inParams;
  }

  public SysLog setInParams(String inParams) {
    this.inParams = inParams;
    return this;
  }


  /**
   * 出参
   */
  public String getOutParams() {
    return outParams;
  }

  public SysLog setOutParams(String outParams) {
    this.outParams = outParams;
    return this;
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
