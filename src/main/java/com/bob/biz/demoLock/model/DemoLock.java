package com.bob.biz.demoLock.model;

import com.bob.core.base.model.BaseModel;

/**
 * DemoLock
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
public class DemoLock extends BaseModel {

    private String brand;    // 品牌

    /** 品牌 */
    public String getBrand() {
        return brand;
    }

    public DemoLock setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DemoLock");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("brand=").append(this.brand).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
