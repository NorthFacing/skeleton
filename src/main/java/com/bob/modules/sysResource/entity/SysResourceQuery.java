package com.bob.modules.sysResource.entity;

import com.bob.core.utils.page.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Bob on 2016/1/2.
 */
@Setter
@Getter
@ToString
public class SysResourceQuery extends BaseQuery {

  private String name;

}
