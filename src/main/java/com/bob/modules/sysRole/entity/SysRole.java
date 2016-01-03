package com.bob.modules.sysRole.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysRole
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public class SysRole extends BaseEntity {

    private String name;    //  名称
    private String description;    //  描述
    private Integer status;    //  状态


    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public SysRole setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    public SysRole setDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    public SysRole setStatus(Integer status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
