package com.bob.modules.sysMonitor.service.impl;

import com.bob.core.utils.javaUtil.Calculate;
import com.bob.modules.sysMonitor.entity.SysMonitor;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysMonitorInfo
 *
 * @author Bob
 * @Date 2016-4-8 14:06:41
 * @since v0.1
 */
public class SysMonitorInfo {

  private static Logger logger = LoggerFactory.getLogger(SysMonitorInfo.class);

  public static SysMonitor usageInfo(Sigar sigar) {
    SysMonitor entity = new SysMonitor();
    try {
      Runtime r = Runtime.getRuntime();
      entity.setJvmUsage(Math.round(Calculate.div(r.totalMemory() - r.freeMemory(), r.totalMemory(), 2) * 100));// JVM使用率

      Mem mem = sigar.getMem();
      // 内存总量
      entity.setRamUsage(Math.round(Calculate.div(mem.getUsed(), mem.getTotal(), 2) * 100));// 内存使用率

      List<Map<String, Long>> cpu = cpuInfos(sigar);
      double b = 0.0;
      for (Map m : cpu) {
        b += Double.valueOf(m.get("cpuTotal") + "");
      }
      entity.setCpuUsage(Math.round(b / cpu.size()));// cpu使用率
    } catch (Exception e) {
      logger.error("获取系统使用情况时出错：", e);
    }
    return entity;
  }

  public static List<Map<String, Long>> cpuInfos(Sigar sigar) {
    List<Map<String, Long>> cpuMaps = new ArrayList<>();
    try {
      CpuPerc cpuList[] = sigar.getCpuPercList();
      for (CpuPerc cpuPerc : cpuList) {
        Map<String, Long> cpu = new HashMap();
        cpu.put("cpuUserUse", Math.round(cpuPerc.getUser() * 100));// 用户使用率
        cpu.put("cpuSysUse", Math.round(cpuPerc.getSys() * 100));// 系统使用率
        cpu.put("cpuWait", Math.round(cpuPerc.getWait() * 100));// 当前等待率
        cpu.put("cpuFree", Math.round(cpuPerc.getIdle() * 100));// 当前空闲率
        cpu.put("cpuTotal", Math.round(cpuPerc.getCombined() * 100));// 总的使用率
        cpuMaps.add(cpu);
      }
    } catch (Exception e) {
      logger.error("获取CPU使用情况时出错：", e);
    }
    return cpuMaps;
  }

}