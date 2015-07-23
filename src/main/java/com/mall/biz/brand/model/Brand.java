package com.mall.biz.brand.model;

import com.mall.core.base.model.BaseModel;

/**
 * 品牌
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
public class Brand extends BaseModel {

    private String name;    // 品牌名称
    private String description;    // 品牌描述
    private boolean isDelete;    // 是否删除

    /** 品牌名称 */
    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    /** 品牌描述 */
    public String getDescription() {
        return description;
    }

    public Brand setDescription(String description) {
        this.description = description;
        return this;
    }

    /** 是否删除 */
    public boolean getIsDelete() {
        return isDelete;
    }

    public Brand setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Brand");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
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
