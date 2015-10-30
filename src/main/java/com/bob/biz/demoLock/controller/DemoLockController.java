package com.bob.biz.demoLock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.model.DemoLockVo;
import com.bob.biz.demoLock.service.DemoLockService;

/**
 * DemoLockController
 *
 * @author Bob
 * @Date 2015-9-11 14:54:56
 * @since v0.0.1
 */
@Controller
public class DemoLockController extends BaseController {

    @Autowired
    private DemoLockService demoLockService;

    @ResponseBody
    @RequestMapping(value = "/demoLock/save", method = RequestMethod.POST)
    public Result save(@Validated DemoLock demoLock) {
        demoLockService.save(demoLock);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoById", method = RequestMethod.GET)
    public Result getById(String id) {
        return Result.success(demoLockService.getVoById(id));
    }

    @RequestMapping(value = "/demoLock/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/demoLock/list";
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoList", method = RequestMethod.GET)
    public Result getList(DemoLockVo demoLockVo) {
        return Result.success(demoLockService.getVoList(demoLockVo));
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoPage", method = RequestMethod.GET)
    public Result getPage(DemoLockVo demoLockVo) {
        PageInfo<DemoLockVo> pageInfo = demoLockService.getVoPage(demoLockVo.getPage(), demoLockVo.getRows(),
                demoLockVo);
        return Result.success(pageInfo);
    }

}
