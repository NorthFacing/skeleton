package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/sysUser/list")
    public String list() {
        return "/sysUser/list";
    }

    @RequestMapping(value = "/sysUser/page")
    public Map<String, Object> page(SysUserQuery query) {
        query = sysUserService.query(query);
        return PageUtil.convertPage(query);
    }

}

