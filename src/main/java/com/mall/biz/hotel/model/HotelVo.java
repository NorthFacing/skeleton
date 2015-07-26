package com.mall.biz.hotel.model;

import com.mall.core.contants.Constants;

/**
 * HotelVo
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 15:14:35
 */
public class HotelVo extends Hotel {

    private String orgName;
    private String orgFullName;
    private String managerName;

    /** 排序字段 */
    private String sidx;
    /** 排序方式：DESC、ASC */
    private String sord;
    /** pageSize */
    private Integer rows = Constants.pageSize;
    /** pageNum */
    private Integer page = Constants.pageNum;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
