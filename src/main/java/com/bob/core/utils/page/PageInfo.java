package com.bob.core.utils.page;


import com.bob.core.contants.Constants;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bob on 2015/12/30.
 */

public class PageInfo<T> implements Serializable {

  private Integer current;// 页码
  private Integer rowCount;//页面大小
  private Long total;     //总数
  private List<T> rows;//结果集

  private Integer startRow;// 起始行
  private Integer endRow;// 末行
  private Integer totalPage;    // 总页数

  /**
   * 计算总页数
   */
  private void calculatePages() {
    if (rowCount > 0) {
      totalPage = (int) (total / rowCount + ((total % rowCount == 0) ? 0 : 1));
    } else {
      totalPage = 0;
    }
  }

  /**
   * 校验查询的页数
   *
   * @Param current 页码
   */
  private void checkPageNum(Integer current) {
    if (current <= 0) {
      this.current = 1;
    } else if (current > totalPage) {
      this.current = totalPage;
    } else {
      this.current = current;
    }
  }

  /**
   * 计算起止行号
   */
  private void calculateStartAndEndRow() {
    this.startRow = this.current > 0 ? (this.current - 1) * this.rowCount : 0;
    this.endRow = this.startRow + this.rowCount * (this.current > 0 ? 1 : 0);
  }

  public Integer getCurrent() {
    return current;
  }

  public void setCurrent(Integer current) {
    this.current = (current == null) ? Constants.PAGE_NUM : current;
  }

  public Integer getRowCount() {
    return rowCount;
  }

  public void setRowCount(Integer rowCount) {
    this.rowCount = (rowCount == null) ? Constants.PAGE_SIZE : rowCount;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = (null == total) ? 0 : total;
    // 计算总页数
    calculatePages();
    // 校验查询的页数
    checkPageNum(current);
    // 计算查询的起止行数
    calculateStartAndEndRow();
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public Integer getEndRow() {
    return endRow;
  }

  public Integer getStartRow() {
    return startRow;
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> result) {
    this.rows = result;
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
