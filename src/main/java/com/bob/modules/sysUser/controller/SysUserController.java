package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.web.Result;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * SysUserController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:42
 * @since v0.1
 */
@Slf4j
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
  public String update(Model model, @RequestParam String id) {
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
  public String select(Model model, @RequestParam String id) {
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

  /**
   * 表单数据是否合法
   *
   * @param entity 需要验证的参数：userName 或者 nickName
   * @return true：合法：false：非法
   */
  @ResponseBody
  @RequestMapping(value = "/sysUser/validateCheck")
  public boolean existCheck(SysUser entity) {
    if (StringUtils.isEmpty(entity.getId())) { // 防止传入的是“”字符串
      entity.setId(null);
    }
    try {
      SysUser select = sysUserService.select(entity);
      if (null == select) { // 没有重复数据
        return true;
      }
      if (select.getId().equals(entity.getId())) { // id相同说明是同一条数据
        return true;
      }
      return false;
    } catch (Exception e) {
      log.error("[SysUserController]-existCheck error: \n params:={}\n{}", entity.toString(), e);
      return false;
    }
  }

  /**
   * 更改密码
   *
   * @param entity id 和 password不能为空
   * @return 更新结果
   */
  @ResponseBody
  @RequestMapping(value = "/sysUser/changePassWord")
  public Result changePwd(SysUser entity, HttpServletRequest request) {
    Result result = Result.fail();
    if (StringUtils.isEmpty(entity.getId())) {
      result.setMsg("参数错误！");
      return result;
    }
    if (StringUtils.isEmpty(entity.getPassWord())) {
      result.setMsg("新密码不能为空！");
      return result;
    }
    // TODO 如果更改的是当前用户，需要重新登录
    result = sysUserService.update(entity);
    // TODO 如果更改的是当前用户，需要重新登录
    return result;
  }

  /**
   * 修改密码页面
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/sysUser/changePassWordPage")
  public String changePassWordPage(String id, Model model) {
    model.addAttribute("id", id);
    return "/sysUser/changePassWord";
  }

  /**
   * 密码是否正确
   *
   * @param entity 需要验证的参数：id && passWord
   * @return true：合法：false：非法
   */
  @ResponseBody
  @RequestMapping(value = "/sysUser/passWordCheck")
  public boolean passWordCheck(SysUser entity) {
    if (StringUtils.isEmpty(entity.getId())) { // 防止传入的是“”字符串
      return false;
    }
    try {
      SysUser select = sysUserService.select(entity);
      if (null == select) {// 找到说明密码不正确
        return false;
      }
      return true;
    } catch (Exception e) {
      log.error("[SysUserController]-existCheck error: \n params:={}\n{}", entity.toString(), e);
      return false;
    }
  }

}

