package com.bob.modules.sysUser.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.mapper.SysRoleMapper;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.mapper.SysUserMapper;
import com.bob.modules.sysUser.service.SysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysUserServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysUserService")
public class SysUserServiceImpl
    extends BaseServiceImpl<SysUser>
    implements SysUserService {

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private SysUserMapper sysUserMapper;
  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private SysRoleMapper sysRoleMapper;
  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private SysResourceMapper sysResourceMapper;

  @Override
  public SysUser getEntity() {
    return new SysUser();
  }

  @Override
  public BaseMapper<SysUser> getMapper() {
    return sysUserMapper;
  }

  public SysUser getByUserName(String name) {
    return sysUserMapper.getByUserName(name);
  }

  @Override
  public List<SysRole> getRolesByUserId(String uId) {
    if (StringUtils.isNotEmpty(uId)) {
      return sysRoleMapper.getRolesByUserId(uId);
    }
    return new ArrayList<>();
  }

  @Override
  public Set<String> getRolesNameByUserId(String uId) {
    Set<String> names = new HashSet<>();
    List<SysRole> roles = this.getRolesByUserId(uId);
    prepareRoles(roles, names);
    return names;
  }

  @Override
  public Set<String> getAllRolesName() {
    List<SysRole> roles = sysRoleMapper.getAllRoles();
    Set<String> names = new HashSet<>();
    prepareRoles(roles, names);
    return names;
  }

  private void prepareRoles(List<SysRole> roles, Set<String> names) {
    if (CollectionUtils.isNotEmpty(roles)) {
      for (SysRole role : roles) {
        if (null != role.getName()) {
          names.add(role.getName());
        }
      }
    }
  }


  @Override
  public List<SysResource> getPermissionsByUserId(String uId) {
    if (StringUtils.isNotEmpty(uId)) {
      return sysResourceMapper.getResourcesByUserId(uId);
    }
    return new ArrayList<>();
  }

  @Override
  public Set<String> getPermissionsNameByUserId(String uId) {
    Set<String> shiroKey = new HashSet<>();
    List<SysResource> resources = this.getPermissionsByUserId(uId);
    preparePermission(resources, shiroKey);
    return shiroKey;
  }

  @Override
  public Set<String> getAllPermissions() {
    List<SysResource> resources = sysResourceMapper.getAllResources();
    Set<String> shiroKey = new HashSet<>();
    preparePermission(resources, shiroKey);
    return shiroKey;
  }

  private void preparePermission(List<SysResource> resources, Set<String> shiroKey) {
    if (CollectionUtils.isNotEmpty(resources)) {
      for (SysResource resource : resources) {
        if (null != resource.getShiroKey()) {
          shiroKey.add(resource.getShiroKey());
        }
      }
    }
  }

}