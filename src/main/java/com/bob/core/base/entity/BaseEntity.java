package com.bob.core.base.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.bob.core.contants.Constants;

public class BaseEntity implements Serializable {

    @Id
    protected String id;
    protected LocalDateTime createTime;
    protected String createUser;
    protected LocalDateTime updateTime;
    protected String updateUser;

    // 分页查询字段
    @Transient
    private String sidx;// 排序字段
    @Transient
    private String sord;// 排序方式：DESC、ASC
    @Transient
    private Integer rows = Constants.pageSize;// pageSize
    @Transient
    private Integer page = Constants.pageNum;// pageNum
    // 公用时间查询字段
    @Transient
    private LocalDateTime createTimeStart;
    @Transient
    private LocalDateTime createTimeEnd;
    @Transient
    protected LocalDateTime updateTimeStart;
    @Transient
    protected LocalDateTime updateTimeEnd;

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

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public LocalDateTime getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(LocalDateTime updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public LocalDateTime getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(LocalDateTime updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}