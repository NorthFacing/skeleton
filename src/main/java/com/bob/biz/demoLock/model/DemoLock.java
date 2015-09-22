package com.bob.biz.demoLock.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

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
        return ReflectionToStringBuilder.toString(this);
    }
}
