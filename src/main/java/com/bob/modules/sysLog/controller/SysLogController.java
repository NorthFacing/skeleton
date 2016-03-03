package com.bob.modules.sysLog.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysLog.entity.SysLog;
import com.bob.modules.sysLog.entity.SysLogQuery;
import com.bob.modules.sysLog.service.SysLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SysLogController
 * @since v0.0.1
 * @author Bob
 * @Date 2016-3-3 9:27:06
 */
@Controller
@RequestMapping("/admin")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    public SysLog select(SysLog entity) {
        return sysLogService.select(entity);
    }

    @RequestMapping(value = "/sysLog/add")
        public String add() {
        return "/sysLog/edit";
    }

    @RequestMapping(value = "/sysLog/update")
        public String update(String id, Model model) {
        SysLog entity = sysLogService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysLog/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/sysLog/save")
        public Result save(SysLog entity) {
        Result result = Result.fail();
        sysLogService.save(entity);
        return result.success();
    }

    @RequestMapping(value = "/sysLog/view")
        public String select(String id, Model model) {
        SysLog entity = sysLogService.selectById(id);
        model.addAttribute("entity", entity);
        return "/sysResource/view";
    }

    @ResponseBody
    @RequestMapping(value = "/sysLog/delete")
        public Result delete(String id) {
        Result result = Result.fail();
        sysLogService.deleteById(id);
        return result.success();
    }

    @RequestMapping(value = "/sysLog/list")
        public String list() {
        return "/sysLog/list";
    }

    @ResponseBody
    @RequestMapping(value = "/sysLog/pageData")
        public Map<String, Object> pageData(SysLogQuery query) {
        query = sysLogService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

