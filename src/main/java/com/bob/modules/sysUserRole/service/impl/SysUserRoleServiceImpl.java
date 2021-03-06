package com.bob.modules.sysUserRole.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysUserRole.entity.SysUserRole;
import com.bob.modules.sysUserRole.mapper.SysUserRoleMapper;
import com.bob.modules.sysUserRole.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysUserRoleServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Override
  public SysUserRole getEntity() {
    return new SysUserRole();
  }

  @Override
  public BaseMapper<SysUserRole> getMapper() {
    return sysUserRoleMapper;
  }
}