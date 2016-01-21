package com.bob.modules.sysUserRole.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysUserRole
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public class SysUserRole extends BaseEntity {

  private String userId;    //  用户ID
  private String roleId;    //  角色ID
  private Integer status;    //  状态


  /**
   * 用户ID
   */
  public String getUserId() {
    return userId;
  }

  public SysUserRole setUserId(String userId) {
    this.userId = userId;
    return this;
  }


  /**
   * 角色ID
   */
  public String getRoleId() {
    return roleId;
  }

  public SysUserRole setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }


  /**
   * 状态
   */
  public Integer getStatus() {
    return status;
  }

  public SysUserRole setStatus(Integer status) {
    this.status = status;
    return this;
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
