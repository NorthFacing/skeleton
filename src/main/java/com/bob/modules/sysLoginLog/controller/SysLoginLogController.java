package com.bob.modules.sysLoginLog.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysLoginLog.entity.SysLoginLog;
import com.bob.modules.sysLoginLog.entity.SysLoginLogQuery;
import com.bob.modules.sysLoginLog.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysLoginLogController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:41
 * @since v0.1
 */
@Controller
@RequestMapping("/admin")
public class SysLoginLogController extends BaseController {

  @Autowired
  private SysLoginLogService sysLoginLogService;

  public SysLoginLog select(SysLoginLog entity) {
    return sysLoginLogService.select(entity);
  }

  @RequestMapping(value = "/sysLoginLog/add")
  public String add() {
    return "/sysLoginLog/edit";
  }

  @RequestMapping(value = "/sysLoginLog/update")
  public String update(String id, Model model) {
    SysLoginLog entity = sysLoginLogService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysLoginLog/edit";
  }

  @ResponseBody
  @RequestMapping(value = "/sysLoginLog/save")
  public Result save(SysLoginLog entity) {
    Result result = Result.fail();
    sysLoginLogService.save(entity);
    return result.success();
  }

  @RequestMapping(value = "/sysLoginLog/view")
  public String select(String id, Model model) {
    SysLoginLog entity = sysLoginLogService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysResource/view";
  }

  @ResponseBody
  @RequestMapping(value = "/sysLoginLog/delete")
  public Result delete(String id) {
    Result result = Result.fail();
    sysLoginLogService.deleteById(id);
    return result.success();
  }

  @RequestMapping(value = "/sysLoginLog/list")
  public String list() {
    return "/sysLoginLog/list";
  }

  @ResponseBody
  @RequestMapping(value = "/sysLoginLog/pageData")
  public Map<String, Object> pageData(SysLoginLogQuery query) {
    query = sysLoginLogService.pageData(query);
    return PageUtil.convertPage(query);
  }

}

