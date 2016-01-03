package com.bob.modules.sysUserRole.service.impl;

import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysUserRole.entity.SysUserRole;
import com.bob.modules.sysUserRole.entity.SysUserRoleVo;
import com.bob.modules.sysUserRole.mapper.SysUserRoleMapper;
import com.bob.modules.sysUserRole.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysUserRoleServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRoleVo> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

}