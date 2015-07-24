package com.mall.biz.parameter.model;

import com.mall.core.base.model.BaseModel;

/**
 * parameter
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-24 15:19:21
 */
public class Parameter extends BaseModel {

    private String categoryId;    // 类目ID
    private String name;    // 参数名称
    private String description;  // 参数描述
    private boolean isDelete;    // 是否删除

    /** 类目ID */
    public String getCategoryId() {
        return categoryId;
    }

    public Parameter setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    /** 参数名称 */
    public String getName() {
        return name;
    }

    public Parameter setName(String name) {
        this.name = name;
        return this;
    }

    /** 参数描述 */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** 是否删除 */
    public boolean getIsDelete() {
        return isDelete;
    }

    public Parameter setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Parameter");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("categoryId=").append(this.categoryId).append(", ");
        sb.append("name=").append(this.name).append(", ");
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
