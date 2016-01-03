package com.bob.modules.sysLoginLog.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysLoginLogVo
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */

public class SysLoginLogVo extends SysLoginLog {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
