package com.bob.modules.sysRoleResource.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * SysRoleResource
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysRoleResource extends BaseEntity {

  @NonNull
  private String roleId;    //  角色ID
  @NonNull
  private String resourceId;    //  权限ID
  @NonNull
  private Integer status;    //  状态

}
