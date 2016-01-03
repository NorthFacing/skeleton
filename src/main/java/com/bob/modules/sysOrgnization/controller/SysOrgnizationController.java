package com.bob.modules.sysOrgnization.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysOrgnization.service.SysOrgnizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysOrgnizationController
 *
 * @author Bob
 * @Date 2016-1-3 22:55:44
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysOrgnizationController extends BaseController {

    @Autowired
    private SysOrgnizationService sysOrgnizationService;

}

