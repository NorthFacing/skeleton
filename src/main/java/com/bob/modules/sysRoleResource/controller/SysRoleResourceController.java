package com.bob.modules.sysRoleResource.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import com.bob.modules.sysRoleResource.entity.SysRoleResourceQuery;
import com.bob.modules.sysRoleResource.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysRoleResourceController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:42
 * @since v0.1
 */
@Controller
@RequestMapping("/admin")
public class SysRoleResourceController extends BaseController {

  @Autowired
  private SysRoleResourceService sysRoleResourceService;

  public SysRoleResource select(SysRoleResource entity) {
    return sysRoleResourceService.select(entity);
  }

  @RequestMapping(value = "/sysRoleResource/add")
  public String add() {
    return "/sysRoleResource/edit";
  }

  @RequestMapping(value = "/sysRoleResource/update")
  public String update(Model model,@RequestParam String id) {
    SysRoleResource entity = sysRoleResourceService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysRoleResource/edit";
  }

  @ResponseBody
  @RequestMapping(value = "/sysRoleResource/save")
  public Result save(SysRoleResource entity) {
    Result result = Result.fail();
    sysRoleResourceService.save(entity);
    return result.success();
  }

  @RequestMapping(value = "/sysRoleResource/view")
  public String select(Model model,@RequestParam String id) {
    SysRoleResource entity = sysRoleResourceService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysResource/view";
  }

  @ResponseBody
  @RequestMapping(value = "/sysRoleResource/delete")
  public Result delete(@RequestParam String id) {
    Result result = Result.fail();
    sysRoleResourceService.deleteById(id);
    return result.success();
  }

  @RequestMapping(value = "/sysRoleResource/list")
  public String list() {
    return "/sysRoleResource/list";
  }

  @ResponseBody
  @RequestMapping(value = "/sysRoleResource/pageData")
  public Map<String, Object> pageData(SysRoleResourceQuery query) {
    query = sysRoleResourceService.pageData(query);
    return PageUtil.convertPage(query);
  }

}

