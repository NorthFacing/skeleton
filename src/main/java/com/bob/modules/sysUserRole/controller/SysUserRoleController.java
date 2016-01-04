package com.bob.modules.sysUserRole.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysUserRole.entity.SysUserRole;
import com.bob.modules.sysUserRole.entity.SysUserRoleQuery;
import com.bob.modules.sysUserRole.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysUserRoleController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:42
 * @since v0.1
 */
@Controller
@RequestMapping("/admin")
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    public SysUserRole select(SysUserRole entity) {
        return sysUserRoleService.select(entity);
    }

    @RequestMapping(value = "/sysUserRole/add")
    public String add() {
        return "/sysUserRole/edit";
    }

    @RequestMapping(value = "/sysUserRole/update")
    public String update(String id, Model model) {
        SysUserRole entity = sysUserRoleService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysUserRole/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUserRole/save")
    public Result save(SysUserRole entity) {
        Result result = Result.fail();
        sysUserRoleService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysUserRole/view")
    public String select(String id, Model model) {
        SysUserRole entity = sysUserRoleService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUserRole/delete")
    public Result delete(String id) {
        Result result = Result.fail();
        sysUserRoleService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysUserRole/list")
    public String list() {
        return "/sysUserRole/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysUserRole/pageData")
    public Map<String, Object> pageData(SysUserRoleQuery query) {
        query = sysUserRoleService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

