package com.mall.biz.organization.model;

import com.mall.core.base.model.BaseModel;

/**
 * organization
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 15:19:55
 */
public class Organization extends BaseModel {

    private String pId;    // 父级ID
    private String code;    // 组织CODE
    private String name;    // 组织名称
    private String fullName;    // 组织全称
    private String textName;

    /** 父级ID */
    public String getPId() {
        return pId;
    }

    public Organization setPId(String pId) {
        this.pId = pId;
        return this;
    }

    /** 组织CODE */
    public String getCode() {
        return code;
    }

    public Organization setCode(String code) {
        this.code = code;
        return this;
    }

    /** 组织名称 */
    public String getName() {
        return name;
    }

    public Organization setName(String name) {
        this.name = name;
        return this;
    }

    /** 组织全称 */
    public String getFullName() {
        return fullName;
    }

    public Organization setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    
    public String getTextName() {
        return textName;
    }

    
    public void setTextName(String textName) {
        this.textName = textName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Organization");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("pId=").append(this.pId).append(", ");
        sb.append("code=").append(this.code).append(", ");
        sb.append("name=").append(this.name).append(", ");
        sb.append("fullName=").append(this.fullName).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
