package com.joyoung.biz.operaterecord.entity;

import com.joyoung.core.utils.page.BaseQuery;

public class OperaterecordQuery extends BaseQuery {
  private Integer productCode;
  private String machineId;

  public String getMachineId() {
    return machineId;
  }

  public void setMachineId(String machineId) {
    this.machineId = machineId;
  }

  public Integer getProductCode() {
    return productCode;
  }

  public void setProductCode(Integer productCode) {
    this.productCode = productCode;
  }
}
