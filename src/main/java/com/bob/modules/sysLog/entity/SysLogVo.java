package com.bob.modules.sysLog.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
* SysLogVo
* @since v0.1
* @author Bob
* @Date 2016-3-3 9:27:06
*/

public class SysLogVo extends SysLog {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
