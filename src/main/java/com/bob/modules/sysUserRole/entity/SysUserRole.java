package com.bob.modules.sysUserRole.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysUserRole
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysUserRole extends BaseEntity {

  private String userId;    //  用户ID
  private String roleId;    //  角色ID
  private Integer status;    //  状态

}
