package com.bob.core.utils.page;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;

import com.bob.core.contants.Constants;

/**
 * Created by Bob on 2015/12/30.
 */

public class PageInfo<T> implements Serializable {

    private int pageNum = Constants.pageNum;// 页码
    private int pageSize = Constants.pageSize;//页面大小
    private int startRow;// 起始行
    private int endRow;// 末行
    private long total;     //总数
    private int pages;    // 总页数
    private List<T> result;//结果集

    public PageInfo() {
        super();
    }

    public PageInfo(int pageNum, int pageSize, int total) {

        this.pageSize = pageSize;
        this.total = total;
        // 计算总页数
        calculatePages();
        // 校验查询的页数
        checkPageNum(pageNum);
        // 计算查询的起止行数
        calculateStartAndEndRow();
    }

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
    private void checkPageNum(int pageNum) {
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


    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getStartRow() {
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
