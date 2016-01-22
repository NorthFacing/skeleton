package com.bob.modules.sysResource.entity;

import com.bob.core.utils.page.BaseQuery;

/**
 * Created by Bob on 2016/1/2.
 */
public class SysResourceQuery extends BaseQuery {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
