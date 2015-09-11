package com.bob.biz.demoKey.model;

import com.bob.core.base.model.BaseModel;

/**
 * DemoKey
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
public class DemoKey extends BaseModel {

    private String lockId;    // 锁ID
    private String brand;    // 品牌

    /** 锁ID */
    public String getLockId() {
        return lockId;
    }

    public DemoKey setLockId(String lockId) {
        this.lockId = lockId;
        return this;
    }

    /** 品牌 */
    public String getBrand() {
        return brand;
    }

    public DemoKey setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DemoKey");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("lockId=").append(this.lockId).append(", ");
        sb.append("brand=").append(this.brand).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
