package com.mall.biz.category.controller;

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
import com.mall.biz.category.model.Category;
import com.mall.biz.category.model.CategoryVo;
import com.mall.biz.category.service.CategoryService;

/**
 * CategoryController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Controller
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "/category/edit", method = RequestMethod.POST)
    public AjaxResults<?> edit(@Validated Category category) {
        categoryService.edit(category);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/category/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Category>(categoryService.getById(id));
    }

    @RequestMapping(value = "/category/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "category/list";
    }

    @ResponseBody
    @RequestMapping(value = "/category/getPage", method = RequestMethod.POST)
    public AjaxResults<?> getPage(CategoryVo categoryVo) {
        PageInfo<Category> pageInfo = categoryService.getPage(categoryVo.getPage(), categoryVo.getRows(), categoryVo);
        return new AjaxResults<PageInfo<?>>(pageInfo);
    }
}
