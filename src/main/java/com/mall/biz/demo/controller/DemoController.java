package com.mall.biz.demo.controller;

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
import com.mall.core.base.controller.BaseController;
import com.mall.core.contants.Constants;
import com.mall.core.utils.AjaxResults;
import com.mall.biz.demo.model.Demo;
import com.mall.biz.demo.model.DemoVo;
import com.mall.biz.demo.service.DemoService;

@Controller
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoSerivce;

    @RequestMapping(value = "/demo/add", method = RequestMethod.POST)
    public String add(@Validated Demo demoModel, Model model) {
        demoSerivce.add(demoModel);
        return "demo/view";
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(Integer id) {
        return new AjaxResults<Object>(demoSerivce.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(DemoVo demoModelVo) {
        return new AjaxResults<List<?>>(demoSerivce.getList(demoModelVo));
    }

    @ResponseBody
    @RequestMapping(value = "/demo/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(DemoVo demoModelVo,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(demoSerivce.getPage(pageNum, pageSize, demoModelVo));
    }

    @RequestMapping(value = "/demo/update", method = RequestMethod.POST)
    public String update(@Validated Demo demoModel, Model model) {
        demoSerivce.updateById(demoModel);
        return "demo/view";
    }

    // 测试代码，不需要自动生成
    @RequestMapping(value = "/demo/list", method = RequestMethod.GET)
    public String viewList(Model model) {
        return "demo/list";
    }

}
