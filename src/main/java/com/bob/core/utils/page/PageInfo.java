package com.bob.core.utils.page;

import com.bob.core.contants.Constants;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @param <T> 封装的数据类型
 */
public class PageInfo<T> implements Serializable {

  private static final long serialVersionUID = 1116973189977306736L;

  private Integer current = Constants.PAGE_NUM;// 页码
  private Integer rowCount = Constants.PAGE_SIZE;//页面大小
  private Long total;     //总数
  private List<T> rows;//结果集

  private Integer startRow;// 起始行
  private Integer endRow;// 末行
  private Integer totalPage;    // 总页数

  private void calculatePages() {
    if (rowCount > 0) {
      totalPage = (int) (total / rowCount + ((total % rowCount == 0) ? 0 : 1));
    } else {
      totalPage = 0;
    }
  }

  private void checkPageNum(Integer current) {
    if (current <= 0) {
      this.current = 1;
    } else if (current > totalPage) {
      this.current = totalPage;
    } else {
      this.current = current;
    }
  }

  private void calculateStartAndEndRow() {
    this.startRow = this.current > 0 ? (this.current - 1) * this.rowCount : 0;
    this.endRow = this.startRow + this.rowCount * (this.current > 0 ? 1 : 0);
  }

  // =================== getter ===================
  public Integer getCurrent() {
    return current;
  }

  // =================== setTotal opr ===================

  /**
   * 接收请求参数
   *
   * @param current 当前页
   */
  public void setCurrent(Integer current) {
    if (current == null) // 传空值使用默认
      return;
    this.current = current;
  }

  public Integer getRowCount() {
    return rowCount;
  }

  /**
   * 接收请求参数
   *
   * @param rowCount 每页行数
   */
  public void setRowCount(Integer rowCount) {
    if (rowCount == null) // 传空值使用默认
      return;
    this.rowCount = rowCount;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = (null == total) ? 0l : total;
    // 计算总页数
    calculatePages();
    // 校验并修正查询的页数
    checkPageNum(current);
    // 计算查询的起止行数
    calculateStartAndEndRow();
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> result) {
    this.rows = result;
  }

  public Integer getStartRow() {
    return startRow;
  }

  public Integer getEndRow() {
    return endRow;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
