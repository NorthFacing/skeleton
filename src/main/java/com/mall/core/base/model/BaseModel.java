package com.mall.core.base.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.mall.core.contants.Constants;

public class BaseModel {

    @Id
    protected String id;
    protected LocalDateTime createTime;
    protected String createUser;
    protected LocalDateTime updateTime;
    protected String updateUser;

    /** 排序字段 */
    private String sidx;
    /** 排序方式：DESC、ASC */
    private String sord;
    /** pageSize */
    private Integer rows = Constants.pageSize;
    /** pageNum */
    private Integer page = Constants.pageNum;

    public String getId() {
        return id;
    }

    public BaseModel setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public BaseModel setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public BaseModel setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public BaseModel setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public BaseModel setUpdateUser(String updateUser) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DemoModel");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("updateUser=").append(this.updateUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("sidx=").append(this.sidx).append(", ");
        sb.append("sord=").append(this.sord).append(", ");
        sb.append("rows=").append(this.rows).append(", ");
        sb.append("page=").append(this.page).append(", ");
        sb.append(" ]");
        return sb.toString();
    }

}
