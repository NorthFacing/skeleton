package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.web.Result;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SysUserController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:42
 * @since v0.1
 */
@Controller
@RequestMapping("/admin")
public class SysUserController extends BaseController {

  @Autowired
  private SysUserService sysUserService;

  public SysUser select(SysUser entity) {
    return sysUserService.select(entity);
  }

  @RequiresPermissions("sysUser:add")
  @RequestMapping(value = "/sysUser/add")
  public String add() {
    return "/sysUser/edit";
  }

  @RequiresPermissions("sysUser:update")
  @RequestMapping(value = "/sysUser/update")
  public String update(Model model,@RequestParam String id) {
    SysUser entity = sysUserService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysUser/edit";
  }

  @RequiresPermissions(value = {"sysUser:add", "sysUser:update"}, logical = Logical.OR)
  @ResponseBody
  @RequestMapping(value = "/sysUser/save")
  public Result save(SysUser entity) {
    Result result = Result.fail();
    sysUserService.save(entity);
    return result.success();
  }

  @RequiresPermissions("sysUser:view")
  @RequestMapping(value = "/sysUser/view")
  public String select(Model model,@RequestParam String id) {
    SysUser entity = sysUserService.selectById(id);
    model.addAttribute("entity", entity);
    return "/sysResource/view";
  }

  @RequiresPermissions("sysUser:delete")
  @ResponseBody
  @RequestMapping(value = "/sysUser/delete")
  public Result delete(@RequestParam String id) {
    Result result = Result.fail();
    sysUserService.deleteById(id);
    return result.success();
  }

  @RequiresPermissions("sysUser:list")
  @RequestMapping(value = "/sysUser/list")
  public String list() {
    return "/sysUser/list";
  }

  @RequiresPermissions("sysUser:list")
  @ResponseBody
  @RequestMapping(value = "/sysUser/pageData")
  public Result<SysUserQuery> pageData(SysUserQuery query) {
    query = sysUserService.pageData(query);
    return Result.success(query);
  }

}

