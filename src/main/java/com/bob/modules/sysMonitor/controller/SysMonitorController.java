package com.bob.modules.sysMonitor.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.web.Result;
import com.bob.modules.sysMonitor.entity.SysMonitor;
import com.bob.modules.sysMonitor.service.impl.SysMonitorInfo;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SysMonitorController
 *
 * @author Bob
 * @Date 2016-4-8 14:06:41
 * @since v0.1
 */
@Controller
@RequestMapping("/admin")
public class SysMonitorController extends BaseController {

  @RequestMapping(value = "/sysMonitor/usageInfo")
  public String add() {
    return "/sysMonitor/usageInfo";
  }

  @ResponseBody
  @RequestMapping(value = "/sysMonitor/usageInfoData")
  public Result usageInfoData() {
    Result result = Result.fail();
    SysMonitor sysMonitor = SysMonitorInfo.usageInfo(new Sigar());
    return result.success(sysMonitor);
  }

}

