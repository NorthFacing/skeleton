package com.bob.modules.sysResource.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.bob.core.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

public class SysResource extends BaseEntity {

    private String parentId;    //  父节点ID
    private String name;    //  名称
    private String resUrl;    //  资源路径
    private Integer type;    //  类型
    private Integer level;    //  层级
    private String description;    //  描述
    private Integer status;    //  状态


    /**
     * 父节点ID
     */
    @Transient
    public String getParentId() {
        return parentId;
    }

    public SysResource setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }


    /**
     * 名称
     */
    @Transient
    public String getName() {
        return name;
    }

    public SysResource setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * 资源路径
     */
    @Transient
    public String getResUrl() {
        return resUrl;
    }

    public SysResource setResUrl(String resUrl) {
        this.resUrl = resUrl;
        return this;
    }


    /**
     * 类型
     */
    @Transient
    public Integer getType() {
        return type;
    }

    public SysResource setType(Integer type) {
        this.type = type;
        return this;
    }


    /**
     * 层级
     */
    @Transient
    public Integer getLevel() {
        return level;
    }

    public SysResource setLevel(Integer level) {
        this.level = level;
        return this;
    }


    /**
     * 描述
     */
    @Transient
    public String getDescription() {
        return description;
    }

    public SysResource setDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    public SysResource setStatus(Integer status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
