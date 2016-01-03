package com.bob.modules.sysUserRole.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysUserRole.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * SysUserRoleController
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.0.1
 */
@Controller
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

}

