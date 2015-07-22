package com.mall.biz.brand.controller;

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
import com.mall.biz.brand.model.Brand;
import com.mall.biz.brand.service.BrandService;

/**
 * brandController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Controller
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @ResponseBody
    @RequestMapping(value = "/brand/edit", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Brand brand) {
        brandService.edit(brand);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/brand/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Brand>(brandService.getById(id));
    }

    @RequestMapping(value = "/brand/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "brand/list";
    }

    @ResponseBody
    @RequestMapping(value = "/brand/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Brand brand) {
        return new AjaxResults<List<?>>(brandService.getList(brand));
    }

    @ResponseBody
    @RequestMapping(value = "/brand/getPage", method = RequestMethod.GET)
    public AjaxResults<?> getPage(Brand brand,
        @RequestParam(value = "page", required = false, defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(value = "rows", required = false, defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(brandService.getPage(pageNum, pageSize, brand));
    }

    @ResponseBody
    @RequestMapping(value = "/brand/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Brand brand) {
        brandService.updateById(brand);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/brand/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        brandService.delById(id);
        return AjaxResults.success();
    }

}
