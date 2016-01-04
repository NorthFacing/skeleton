package com.bob.modules.sysRoleResource.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import com.bob.modules.sysRoleResource.entity.SysRoleResourceQuery;
import com.bob.modules.sysRoleResource.entity.SysRoleResourceVo;
import com.bob.modules.sysRoleResource.mapper.SysRoleResourceMapper;
import com.bob.modules.sysRoleResource.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysRoleResourceServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysRoleResourceService")
public class SysRoleResourceServiceImpl
        extends BaseServiceImpl<SysRoleResource, SysRoleResourceVo, SysRoleResourceQuery>
        implements SysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public SysRoleResource getEntity() {
        return new SysRoleResource();
    }

    @Override
    public BaseMapper<SysRoleResource, SysRoleResourceVo, SysRoleResourceQuery> getMapper() {
        return sysRoleResourceMapper;
    }

}