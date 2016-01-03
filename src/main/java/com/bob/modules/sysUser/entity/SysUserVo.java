package com.bob.modules.sysUser.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysUserVo
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */

public class SysUserVo extends SysUser {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
