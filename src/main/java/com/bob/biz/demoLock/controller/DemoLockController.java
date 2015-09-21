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
import com.bob.core.utils.AjaxResults;
import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.model.DemoLockVo;
import com.bob.biz.demoLock.service.DemoLockService;

/**
 * DemoLockController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
@Controller
public class DemoLockController extends BaseController {

    @Autowired
    private DemoLockService demoLockService;

    @ResponseBody
    @RequestMapping(value = "/demoLock/save", method = RequestMethod.POST)
    public AjaxResults save(@Validated DemoLock demoLock) {
        demoLockService.save(demoLock);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoById", method = RequestMethod.GET)
    public AjaxResults getById(String id) {
        return new AjaxResults(demoLockService.getVoById(id));
    }

    @RequestMapping(value = "/demoLock/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/demoLock/list";
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoList", method = RequestMethod.GET)
    public AjaxResults getList(DemoLockVo demoLockVo) {
        return new AjaxResults(demoLockService.getVoList(demoLockVo));
    }

    @ResponseBody
    @RequestMapping(value = "/demoLock/getVoPage", method = RequestMethod.GET)
    public AjaxResults getPage(DemoLockVo demoLockVo) {
        PageInfo<DemoLockVo> pageInfo = demoLockService.getVoPage(demoLockVo.getPage(), demoLockVo.getRows(),
            demoLockVo);
        return new AjaxResults(pageInfo);
    }

}
