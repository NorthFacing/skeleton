package com.bob.modules.sysMonitor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Bob on 2016/4/8.
 */
@Setter
@Getter
@ToString
public class SysMonitor {

  private long jvmUsage;
  private long ramUsage;
  private long cpuUsage;

}
