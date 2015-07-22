package com.mall.biz;

import java.util.ArrayList;
import java.util.List;

public class JqGrid {

    public int pageNum;
    public int totalPage;
    public int recordsNum;
    public List<Row> rows = new ArrayList<Row>();

    public int getPage() {
        return pageNum;
    }

    public void setPage(int page) {
        this.pageNum = page;
    }

    public int getTotal() {
        return totalPage;
    }

    public void setTotal(int total) {
        this.totalPage = total;
    }

    public int getRecords() {
        return recordsNum;
    }

    public void setRecords(int records) {
        this.recordsNum = records;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
