package com.bob.modules.sysRole.entity;

import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * SysRoleVo
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */

@Setter
@Getter
@ToString
public class SysRoleVo extends SysRole {

  private List<String> resourceIds;

  private List<SysRoleResource> resourceList;

}
