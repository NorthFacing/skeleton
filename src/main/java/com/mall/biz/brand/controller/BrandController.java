package com.mall.biz.brand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mall.core.base.controller.BaseController;
import com.mall.core.utils.AjaxResults;
import com.mall.biz.brand.model.Brand;
import com.mall.biz.brand.model.BrandVo;
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
    public AjaxResults<?> edit(@Validated Brand brand) {
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
    @RequestMapping(value = "/brand/getPage", method = RequestMethod.GET)
    public AjaxResults<?> getPage(BrandVo brandVo) {
        return new AjaxResults<PageInfo<?>>(brandService.getPage(brandVo));
    }

}
