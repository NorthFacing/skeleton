package com.bob.modules.sysOrgnization.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysOrgnization.entity.SysOrgnization;
import com.bob.modules.sysOrgnization.entity.SysOrgnizationQuery;
import com.bob.modules.sysOrgnization.service.SysOrgnizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysOrgnizationController
 *
 * @author Bob
 * @Date 2016-1-4 16:06:41
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysOrgnizationController extends BaseController {

    @Autowired
    private SysOrgnizationService sysOrgnizationService;

    public SysOrgnization select(SysOrgnization entity) {
        return sysOrgnizationService.select(entity);
    }

    @RequestMapping(value = "/sysOrgnization/add")
    public String add() {
        return "/sysOrgnization/edit";
    }

    @RequestMapping(value = "/sysOrgnization/update")
    public String update(String id, Model model) {
        SysOrgnization entity = sysOrgnizationService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysOrgnization/edit";
    }

    @RequestMapping(value = "/sysOrgnization/save")
    public Result save(SysOrgnization entity) {
        Result result = Result.fail();
        sysOrgnizationService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysOrgnization/view")
    public String select(String id, Model model) {
        SysOrgnization entity = sysOrgnizationService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @RequestMapping(value = "/sysOrgnization/delete")
    public Result delete(String id) {
        Result result = Result.fail();
        sysOrgnizationService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysOrgnization/list")
    public String list() {
        return "/sysOrgnization/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysOrgnization/pageData")
    public Map<String, Object> pageData(SysOrgnizationQuery query) {
        query = sysOrgnizationService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

