package com.bob.modules.sysUser.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.mapper.SysRoleMapper;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.entity.SysUserVo;
import com.bob.modules.sysUser.mapper.SysUserMapper;
import com.bob.modules.sysUser.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        extends BaseServiceImpl<SysUser, SysUserVo, SysUserQuery>
        implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public SysUser getEntity() {
        return new SysUser();
    }

    @Override
    public List<SysRole> getRolesByUserId(String uId) {
        return sysRoleMapper.getRolesByUserId(uId);
    }

    @Override
    public Set<String> getRolesNameByUserId(String uId) {
        Set<String> names = new HashSet<>();
        if (StringUtils.isNotEmpty(uId)) {
            List<SysRole> roles = this.getRolesByUserId(uId);
            for (SysRole role : roles) {
                names.add(role.getName());
            }
        }
        return names;
    }

    @Override
    public List<SysResource> getResourcesByUserId(String uId) {
        return sysResourceMapper.getResourcesByUserId(uId);
    }

    @Override
    public Set<String> getResourcesNameByUserId(String uId) {
        Set<String> names = new HashSet<>();
        if (StringUtils.isNotEmpty(uId)) {
            List<SysResource> resources = this.getResourcesByUserId(uId);
            for (SysResource resource : resources) {
                names.add(resource.getName());
            }
        }
        return names;
    }

    public SysUser getByUserName(String name){
        return sysUserMapper.getByUserName(name);
    }

    @Override
    public BaseMapper<SysUser, SysUserVo, SysUserQuery> getMapper() {
        return sysUserMapper;
    }

}