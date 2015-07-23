package com.mall.biz.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.model.Simple;
import com.mall.biz.simple.model.SimpleVo;
import com.mall.biz.simple.service.SimpleService;
import com.mall.core.utils.AjaxResults;

@Controller
public class SimpleController {

    @Autowired
    private SimpleService demoSerivce;

    @RequestMapping(value = "/simple/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Simple model) {
        demoSerivce.add(model);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Object>(demoSerivce.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Simple model) {
        return new AjaxResults<List<?>>(demoSerivce.getList(model));
    }

    @ResponseBody
    @RequestMapping(value = "/simple/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(SimpleVo modelVo) {
        return new AjaxResults<PageInfo<?>>(demoSerivce.getPage(modelVo));
    }

    @RequestMapping(value = "/simple/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Simple model) {
        demoSerivce.updateById(model);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/simple/delById", method = RequestMethod.POST)
    public AjaxResults<?> delById(String id) {
        demoSerivce.delById(id);
        return AjaxResults.success();
    }

}
