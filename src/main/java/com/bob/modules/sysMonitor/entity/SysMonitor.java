package com.bob.modules.sysMonitor.entity;

/**
 * Created by Bob on 2016/4/8.
 */
public class SysMonitor {

  private long jvmUsage;
  private long ramUsage;
  private long cpuUsage;

  public long getJvmUsage() {
    return jvmUsage;
  }

  public void setJvmUsage(long jvmUsage) {
    this.jvmUsage = jvmUsage;
  }

  public long getRamUsage() {
    return ramUsage;
  }

  public void setRamUsage(long ramUsage) {
    this.ramUsage = ramUsage;
  }

  public long getCpuUsage() {
    return cpuUsage;
  }

  public void setCpuUsage(long cpuUsage) {
    this.cpuUsage = cpuUsage;
  }
}
