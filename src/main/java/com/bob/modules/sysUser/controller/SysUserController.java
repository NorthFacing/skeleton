package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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

    @RequestMapping(value = "/sysUser/add")
    public String add() {
        return "/sysUser/edit";
    }

    @RequestMapping(value = "/sysUser/update")
    public String update(String id, Model model) {
        SysUser entity = sysUserService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysUser/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUser/save")
    public Result save(SysUser entity) {
        Result result = Result.fail();
        sysUserService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysUser/view")
    public String select(String id, Model model) {
        SysUser entity = sysUserService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUser/delete")
    public Result delete(String id) {
        Result result = Result.fail();
        sysUserService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysUser/list")
    public String list() {
        return "/sysUser/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUser/pageData")
    public Map<String, Object> pageData(SysUserQuery query) {
        query = sysUserService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

