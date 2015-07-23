package com.mall.biz.category.model;

import com.mall.core.base.model.BaseModel;

/**
 * category
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
public class Category extends BaseModel {

    private String name;    // 类目名称
    private String code;    // 类目编码
    private String description;    // 类目描述
    private boolean isDelete;    // 是否删除

    /** 类目名称 */
    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    /** 类目编码 */
    public String getCode() {
        return code;
    }

    public Category setCode(String code) {
        this.code = code;
        return this;
    }

    /** 类目描述 */
    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    /** 是否删除 */
    public boolean getIsDelete() {
        return isDelete;
    }

    public Category setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("name=").append(this.name).append(", ");
        sb.append("code=").append(this.code).append(", ");
        sb.append("description=").append(this.description).append(", ");
        sb.append("isDelete=").append(this.isDelete).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
