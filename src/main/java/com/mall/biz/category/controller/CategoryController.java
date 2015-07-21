package com.mall.biz.category.controller;

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
import com.mall.biz.category.model.Category;
import com.mall.biz.category.service.CategoryService;

/**
 * categoryController
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Controller
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Category category) {
        categoryService.add(category);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/category/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Category>(categoryService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/category/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Category category) {
        return new AjaxResults<List<?>>(categoryService.getList(category));
    }

    @ResponseBody
    @RequestMapping(value = "/category/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(Category category,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(categoryService.getPage(pageNum, pageSize, category));
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Category category) {
        categoryService.updateById(category);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/category/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        categoryService.delById(id);
        return AjaxResults.success();
    }

}

