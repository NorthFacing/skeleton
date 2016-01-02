package com.bob.core.utils.page;


import com.bob.core.contants.Constants;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bob on 2015/12/30.
 */

public class PageInfo<T> implements Serializable {

    private Integer pageNum = Constants.pageNum;// 页码
    private Integer pageSize = Constants.pageSize;//页面大小
    private Integer startRow;// 起始行
    private Integer endRow;// 末行
    private Long total;     //总数
    private Integer pages;    // 总页数
    private List<T> result;//结果集

    /**
     * 计算总页数
     */
    private void calculatePages() {
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
    }

    /**
     * 校验查询的页数
     *
     * @Param pageNum 页码
     */
    private void checkPageNum(Integer pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
        } else if (pageNum > pages) {
            this.pageNum = pages;
        } else {
            this.pageNum = pageNum;
        }
    }

    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = (null == total) ? 0 : total;
        // 计算总页数
        calculatePages();
        // 校验查询的页数
        checkPageNum(pageNum);
        // 计算查询的起止行数
        calculateStartAndEndRow();
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
