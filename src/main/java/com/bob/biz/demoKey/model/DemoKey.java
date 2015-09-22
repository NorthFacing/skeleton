package com.bob.biz.demoKey.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

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
        return ReflectionToStringBuilder.toString(this);
    }
}
