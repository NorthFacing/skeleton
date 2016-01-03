package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/login")
    public String login(){
        return "/sysUser/login";
    }

    @RequestMapping(value = "/sysUser/list")
    public String list(){
        return "/sysUser/list";
    }

}

