package com.bob.modules.sysRole.entity;

import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * SysRoleVo
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */

public class SysRoleVo extends SysRole {

    private List<String> resourceIds;

    private List<SysRoleResource> resourceList;

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<SysRoleResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<SysRoleResource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
