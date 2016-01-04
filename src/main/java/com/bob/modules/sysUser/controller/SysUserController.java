package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysUserController
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/sysUser/save")
    public Result save(SysUser entity) {
        Result result = Result.fail();
        return result;
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

