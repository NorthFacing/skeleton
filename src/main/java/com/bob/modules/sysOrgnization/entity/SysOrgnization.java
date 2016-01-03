package com.bob.modules.sysOrgnization.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysOrgnization
 *
 * @author Bob
 * @Date 2016-1-3 22:55:44
 * @since v0.1
 */
public class SysOrgnization extends BaseEntity {

    private String parentId;    //  父节点ID
    private String code;    //  编码
    private String name;    //  名称
    private String fullName;    //  全程
    private Integer status;    //  状态


    /**
     * 父节点ID
     */
    public String getParentId() {
        return parentId;
    }

    public SysOrgnization setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }


    /**
     * 编码
     */
    public String getCode() {
        return code;
    }

    public SysOrgnization setCode(String code) {
        this.code = code;
        return this;
    }


    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public SysOrgnization setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * 全程
     */
    public String getFullName() {
        return fullName;
    }

    public SysOrgnization setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    public SysOrgnization setStatus(Integer status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
