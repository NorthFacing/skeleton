package com.bob.biz.brand.controller;

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
import com.bob.biz.brand.model.Brand;
import com.bob.biz.brand.model.BrandVo;
import com.bob.biz.brand.service.BrandService;

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
    @RequestMapping(value = "/brand/getPage", method = RequestMethod.POST)
    public AjaxResults<?> getPage(BrandVo brandVo) {
        PageInfo<Brand> pageInfo = brandService.getPage(brandVo.getPage(), brandVo.getRows(), brandVo);
        return new AjaxResults<PageInfo<?>>(pageInfo);
    }
}
