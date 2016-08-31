package com.bob.core.utils.page;

import java.time.LocalDateTime;

/**
 * Created by Bob on 2015/12/30.
 */
public class BaseQuery extends PageInfo {

  // 分页查询字段
  protected LocalDateTime updateTimeEnd;
  protected LocalDateTime updateTimeStart;
  private LocalDateTime createTimeStart;
  private LocalDateTime createTimeEnd;

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
