package com.bob.core.base.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {

  @Id
  private String id;
  private LocalDateTime createTime;
  private String createUser;
  private LocalDateTime updateTime;
  private String updateUser;

  public String getId() {
    return id;
  }

  public BaseEntity setId(String id) {
    this.id = id;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public BaseEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getCreateUser() {
    return createUser;
  }

  public BaseEntity setCreateUser(String createUser) {
    this.createUser = createUser;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public BaseEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public BaseEntity setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
    return this;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
