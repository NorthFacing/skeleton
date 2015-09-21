package com.bob.biz.demoKey.controller;

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
import com.bob.biz.demoKey.model.DemoKey;
import com.bob.biz.demoKey.model.DemoKeyVo;
import com.bob.biz.demoKey.service.DemoKeyService;

/**
 * DemoKeyController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
@Controller
public class DemoKeyController extends BaseController {

    @Autowired
    private DemoKeyService demoKeyService;

    @ResponseBody
    @RequestMapping(value = "/demoKey/save", method = RequestMethod.GET)
    public AjaxResults save(@Validated DemoKey demoKey) {
        demoKey = new DemoKey();
        demoKey.setBrand("三环");
        demoKeyService.save(demoKey);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/demoKey/getVoById", method = RequestMethod.GET)
    public AjaxResults getVoById(String id) {
        return new AjaxResults(demoKeyService.getVoById(id));
    }

    @RequestMapping(value = "/demoKey/list", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/demoKey/list";
    }

    @ResponseBody
    @RequestMapping(value = "/demoKey/getList", method = RequestMethod.GET)
    public AjaxResults getList(DemoKeyVo demoKeyVo) {
        return new AjaxResults(demoKeyService.getVoList(demoKeyVo));
    }

    @ResponseBody
    @RequestMapping(value = "/demoKey/getPage", method = RequestMethod.GET)
    public AjaxResults getPage(DemoKeyVo demoKeyVo) {
        PageInfo<DemoKey> pageInfo = demoKeyService.getPage(demoKeyVo.getPage(), demoKeyVo.getRows(), demoKeyVo);
        return new AjaxResults(pageInfo);
    }

}
