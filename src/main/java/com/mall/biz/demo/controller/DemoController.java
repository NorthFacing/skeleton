package com.mall.biz.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mall.core.base.controller.BaseController;
import com.mall.core.contants.Constants;
import com.mall.core.utils.AjaxResults;
import com.mall.biz.demo.model.Demo;
import com.mall.biz.demo.service.DemoService;

@Controller
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoSerivce;

    @RequestMapping(value = "/demo/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Demo model) {
        demoSerivce.add(model);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(Integer id) {
        return new AjaxResults<Demo>(demoSerivce.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Demo model) {
        return new AjaxResults<List<?>>(demoSerivce.getList(model));
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(Demo model,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(demoSerivce.getPage(pageNum, pageSize, model));
    }

    @RequestMapping(value = "/demo/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Demo demoModel) {
        demoSerivce.updateById(demoModel);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/demo/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(Integer id) {
        demoSerivce.delById(id);
        return AjaxResults.success();
    }

}
