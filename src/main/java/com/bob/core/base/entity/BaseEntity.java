package com.bob.core.base.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.Id;

//import javax.persistence.Column;        // @Column
//import javax.persistence.Id;            // @Id
//import javax.persistence.Table;         // @Table
//import javax.persistence.Transient;     // @Transient

public class BaseEntity implements Serializable {

    @Id
    protected String id;
    protected LocalDateTime createTime;
    protected String createUser;
    protected LocalDateTime updateTime;
    protected String updateUser;

    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public BaseEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public BaseEntity setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public BaseEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public BaseEntity setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
