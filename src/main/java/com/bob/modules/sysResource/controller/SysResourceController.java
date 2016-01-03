package com.bob.modules.sysResource.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bob on 2016/1/3.
 */
@Controller
@RequestMapping("/admin")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    public SysResource select(SysResource sysResource) {
        return sysResourceService.select(sysResource);
    }
}
