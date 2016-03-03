package com.bob.modules.sysLog.entity;

import com.bob.core.utils.page.PageInfo;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysLog
 *
 * @author Bob
 * @Date 2016-3-3 9:27:06
 * @since v0.1
 */
public class SysLogQuery extends PageInfo {

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
