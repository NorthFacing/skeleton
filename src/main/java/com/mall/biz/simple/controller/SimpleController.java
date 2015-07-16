package com.mall.biz.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.model.SimpleModel;
import com.mall.biz.simple.service.SimpleService;
import com.mall.core.contants.Constants;
import com.mall.core.utils.AjaxResults;

@Controller
@RequestMapping
public class SimpleController {

    @Autowired
    private SimpleService demoSerivce;

    @RequestMapping(value = "/simple/add", method = RequestMethod.POST)
    public String add(@Validated SimpleModel demoModel, Model model) {
        demoSerivce.add(demoModel);
        return "demo/view";
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getById", method = RequestMethod.GET)
    public AjaxResults<?> DemoModel(String id) {
        return new AjaxResults<Object>(demoSerivce.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(SimpleModel demoModel) {
        return new AjaxResults<List<?>>(demoSerivce.getList(demoModel));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(SimpleModel demoModel,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(demoSerivce.getPage(pageNum, pageSize, demoModel));
    }

    @RequestMapping(value = "/simple/update", method = RequestMethod.POST)
    public String update(@Validated SimpleModel demoModel, Model model) {
        demoSerivce.updateById(demoModel);
        return "demo/view";
    }

    // 测试代码，不需要自动生成
    @RequestMapping(value = "/simple/list", method = RequestMethod.GET)
    public String viewList(Model model) {
        return "demo/list";
    }

}
