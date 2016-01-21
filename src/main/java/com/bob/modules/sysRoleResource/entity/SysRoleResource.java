package com.bob.modules.sysRoleResource.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysRoleResource
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public class SysRoleResource extends BaseEntity {

  private String roleId;    //  角色ID
  private String resourceId;    //  权限ID
  private Integer status;    //  状态


  /**
   * 角色ID
   */
  public String getRoleId() {
    return roleId;
  }

  public SysRoleResource setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }


  /**
   * 权限ID
   */
  public String getResourceId() {
    return resourceId;
  }

  public SysRoleResource setResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }


  /**
   * 状态
   */
  public Integer getStatus() {
    return status;
  }

  public SysRoleResource setStatus(Integer status) {
    this.status = status;
    return this;
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
