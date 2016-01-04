package com.bob.modules.sysRole.service.impl;

import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.entity.SysRoleQuery;
import com.bob.modules.sysRole.entity.SysRoleVo;
import com.bob.modules.sysRole.mapper.SysRoleMapper;
import com.bob.modules.sysRole.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysRoleServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleVo, SysRoleQuery> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

}