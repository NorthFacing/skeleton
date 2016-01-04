package com.bob.modules.sysRole.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.entity.SysRoleQuery;
import com.bob.modules.sysRole.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysRoleController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:42
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    public SysRole select(SysRole entity) {
        return sysRoleService.select(entity);
    }

    @RequestMapping(value = "/sysRole/add")
    public String add() {
        return "/sysRole/edit";
    }

    @RequestMapping(value = "/sysRole/update")
    public String update(String id, Model model) {
        SysRole entity = sysRoleService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysRole/edit";
    }

    @RequestMapping(value = "/sysRole/save")
    public Result save(SysRole entity) {
        Result result = Result.fail();
        sysRoleService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysRole/view")
    public String select(String id, Model model) {
        SysRole entity = sysRoleService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @RequestMapping(value = "/sysRole/delete")
    public Result delete(String id) {
        Result result = Result.fail();
        sysRoleService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysRole/list")
    public String list() {
        return "/sysRole/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysRole/pageData")
    public Map<String, Object> pageData(SysRoleQuery query) {
        query = sysRoleService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

