package com.bob.modules.sysUser.service.impl;

import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.entity.SysUserVo;
import com.bob.modules.sysUser.mapper.SysUserMapper;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysUserServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserVo, SysUserQuery> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

}