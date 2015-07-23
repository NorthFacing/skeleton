package com.mall.biz.brand.model;

import com.mall.core.contants.Constants;

/**
 * BrandVo
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */

public class BrandVo extends Brand {

    /** 排序字段 */
    private String sidx;
    /** 排序方式：DESC、ASC */
    private String sord;
    /** pageSize */
    private Integer rows = Constants.pageSize;
    /** pageNum */
    private Integer page = Constants.pageNum;

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
