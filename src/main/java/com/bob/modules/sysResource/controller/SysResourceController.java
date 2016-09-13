package com.bob.modules.sysResource.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.web.Result;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceQuery;
import com.bob.modules.sysResource.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bob on 2016/1/3.
 */
@Controller
@RequestMapping("/admin")
public class SysResourceController extends BaseController {

  @Autowired
  private SysResourceService sysResourceService;

  public SysResource select(SysResource entity) {
    return sysResourceService.select(entity);
  }

  @RequestMapping(value = "/sysResource/add")
  public String add() {
    return "/sysResource/edit";
  }

  @RequestMapping(value = "/sysResource/update")
  public String update(Model model, @RequestParam String id) {
    SysResource entity = sysResourceService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysResource/edit";
  }

  @ResponseBody
  @RequestMapping(value = "/sysResource/save")
  public Result save(SysResource entity) {
    Result result = Result.fail();
    sysResourceService.save(entity);
    return result.success();
  }

  @RequestMapping(value = "/sysResource/view")
  public String select(Model model, @RequestParam String id) {
    SysResource entity = sysResourceService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysResource/view";
  }

  @ResponseBody
  @RequestMapping(value = "/sysResource/delete")
  public Result delete(@RequestParam String id) {
    Result result = Result.fail();
    sysResourceService.deleteById(id);
    return result.success();
  }

  @RequestMapping(value = "/sysResource/list")
  public String list() {
    return "/sysResource/list";
  }

  @ResponseBody
  @RequestMapping(value = "/sysResource/pageData")
  public SysResourceQuery pageData(SysResourceQuery query) {
    return sysResourceService.pageData(query);
  }

}
