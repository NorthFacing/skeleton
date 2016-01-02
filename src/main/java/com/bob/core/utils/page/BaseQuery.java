package com.bob.core.utils.page;

import java.time.LocalDateTime;

/**
 * Created by Bob on 2015/12/30.
 */
public class BaseQuery extends PageInfo {

    protected LocalDateTime updateTimeStart;
    protected LocalDateTime updateTimeEnd;
    // 分页查询字段
    private String sidx;// 排序字段
    private String sord;// 排序方式：DESC、ASC
    // 公用时间查询字段
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;

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
}
