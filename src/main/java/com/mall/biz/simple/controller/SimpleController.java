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
import com.mall.biz.simple.model.Simple;
import com.mall.biz.simple.service.SimpleService;
import com.mall.core.contants.Constants;
import com.mall.core.utils.AjaxResults;

@Controller
public class SimpleController {

    @Autowired
    private SimpleService demoSerivce;

    @RequestMapping(value = "/simple/add", method = RequestMethod.POST)
    public String add(@Validated Simple simpleModel, Model model) {
        demoSerivce.add(simpleModel);
        return "demo/view";
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(Integer id) {
        return new AjaxResults<Object>(demoSerivce.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Simple simpleModel) {
        return new AjaxResults<List<?>>(demoSerivce.getList(simpleModel));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(Simple simpleModel,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(demoSerivce.getPage(pageNum, pageSize, simpleModel));
    }

    @RequestMapping(value = "/simple/update", method = RequestMethod.POST)
    public String update(@Validated Simple simpleModel, Model model) {
        demoSerivce.updateById(simpleModel);
        return "demo/view";
    }

    // 测试代码，不需要自动生成
    @RequestMapping(value = "/simple/list", method = RequestMethod.GET)
    public String viewList(Model model) {
        return "demo/list";
    }

}
