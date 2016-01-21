package com.bob.modules.sysUser.service;

import com.bob.core.base.service.BaseService;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.entity.SysUserVo;

import java.util.List;
import java.util.Set;

/**
 * SysUserService
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysUserService extends BaseService<SysUser, SysUserVo, SysUserQuery> {

  SysUser getByUserName(String name);

  List<SysRole> getRolesByUserId(String uId);

  Set<String> getRolesNameByUserId(String id);

  Set<String> getAllRolesName();

  List<SysResource> getPermissionsByUserId(String id);

  Set<String> getPermissionsNameByUserId(String id);

  Set<String> getAllPermissions();
}