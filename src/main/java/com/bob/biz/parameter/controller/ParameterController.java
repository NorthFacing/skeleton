package com.bob.biz.parameter.controller;

import java.util.List;

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
import com.bob.biz.parameter.model.Parameter;
import com.bob.biz.parameter.model.ParameterVo;
import com.bob.biz.parameter.service.ParameterService;

/**
 * parameterController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-24 15:19:21
 */
@Controller
public class ParameterController extends BaseController {

    @Autowired
    private ParameterService parameterService;

    @ResponseBody
    @RequestMapping(value = "/parameter/edit", method = RequestMethod.POST)
    public AjaxResults<?> edit(@Validated Parameter parameter) {
        parameterService.edit(parameter);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/parameter/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Parameter>(parameterService.getById(id));
    }

    @RequestMapping(value = "/parameter/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "parameter/list";
    }

    @ResponseBody
    @RequestMapping(value = "/parameter/getList", method = RequestMethod.POST)
    public AjaxResults<List<?>> getList(ParameterVo parameterVo) {
        return new AjaxResults<List<?>>(parameterService.getList(parameterVo));
    }

    @ResponseBody
    @RequestMapping(value = "/parameter/getPage", method = RequestMethod.POST)
    public AjaxResults<PageInfo<?>> getPage(ParameterVo parameterVo) {
        PageInfo<Parameter> pageInfo = parameterService.getPage(parameterVo.getPage(), parameterVo.getRows(),
            parameterVo);
        return new AjaxResults<PageInfo<?>>(pageInfo);
    }
}
