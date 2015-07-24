package com.mall.biz.parameter.model;

import com.mall.core.contants.Constants;

public class ParameterVo extends Parameter {

    private String categoryName;

    /** 排序字段 */
    private String sidx;
    /** 排序方式：DESC、ASC */
    private String sord;
    /** pageSize */
    private Integer rows = Constants.pageSize;
    /** pageNum */
    private Integer page = Constants.pageNum;

    /** 类目名称  */
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

}
