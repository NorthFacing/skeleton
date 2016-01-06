package com.bob.modules.sysResource.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysResource
 *
 * @author Bob
 * @Date 2016-1-6 16:03:06
 * @since v0.1
 */
public class SysResource extends BaseEntity {

    private String parentId;    //  父节点ID
    private String name;    //  名称
    private String type;    //  类型：1，菜单权限；2，按钮权限
    private String shiroKey;    //  shiro判断资源权限标识符
    private String resUrl;    //  菜单路径（菜单类型时必填）
    private Integer resLevel;    //  菜单层级（菜单类型时必填）
    private Integer resPriority;    //  菜单顺序（菜单类型时必填）
    private String resCode;    //  菜单CODE（自动生成）
    private String description;    //  描述
    private Integer status;    //  状态


    /**
     * 父节点ID
     */
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
    public String getName() {
        return name;
    }

    public SysResource setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * 类型：1，菜单权限；2，按钮权限
     */
    public String getType() {
        return type;
    }

    public SysResource setType(String type) {
        this.type = type;
        return this;
    }


    /**
     * shiro判断资源权限标识符
     */
    public String getShiroKey() {
        return shiroKey;
    }

    public SysResource setShiroKey(String shiroKey) {
        this.shiroKey = shiroKey;
        return this;
    }


    /**
     * 菜单路径（菜单类型时必填）
     */
    public String getResUrl() {
        return resUrl;
    }

    public SysResource setResUrl(String resUrl) {
        this.resUrl = resUrl;
        return this;
    }


    /**
     * 菜单层级（菜单类型时必填）
     */
    public Integer getResLevel() {
        return resLevel;
    }

    public SysResource setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
        return this;
    }


    /**
     * 菜单顺序（菜单类型时必填）
     */
    public Integer getResPriority() {
        return resPriority;
    }

    public SysResource setResPriority(Integer resPriority) {
        this.resPriority = resPriority;
        return this;
    }


    /**
     * 菜单CODE（自动生成）
     */
    public String getResCode() {
        return resCode;
    }

    public SysResource setResCode(String resCode) {
        this.resCode = resCode;
        return this;
    }


    /**
     * 描述
     */
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
