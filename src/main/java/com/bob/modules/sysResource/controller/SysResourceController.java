package com.bob.modules.sysResource.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceQuery;
import com.bob.modules.sysResource.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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

    @RequestMapping(value = "/sysResource/add")
    public String add() {
        return "/sysResource/edit";
    }

    @RequestMapping(value = "/sysResource/update")
    public String update(String id, Model model) {
        SysResource entity = sysResourceService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/edit";
    }

    @RequestMapping(value = "/sysResource/save")
    public Result save(SysResource entity) {
        Result result = Result.fail();
        sysResourceService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysResource/view")
    public String select(String id, Model model) {
        SysResource entity = sysResourceService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @RequestMapping(value = "/sysResource/delete")
    public Result delete(String id) {
        Result result = Result.fail();
        sysResourceService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysResource/list")
    public String list() {
        return "/sysResource/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysResource/pageData")
    public Map<String, Object> pageData(SysResourceQuery query) {
        query = sysResourceService.pageData(query);
        return PageUtil.convertPage(query);
    }


}
