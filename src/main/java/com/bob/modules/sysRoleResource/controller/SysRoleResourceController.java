package com.bob.modules.sysRoleResource.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysRoleResource.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysRoleResourceController
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysRoleResourceController extends BaseController {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

}

