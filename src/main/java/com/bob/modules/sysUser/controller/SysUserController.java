package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * SysUserController
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.0.1
 */
@Controller
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

}

