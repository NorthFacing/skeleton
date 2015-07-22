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
import com.mall.biz.JqGrid;
import com.mall.biz.Result;
import com.mall.biz.Row;
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

    @RequestMapping(value = "/brand/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Brand brand) {
        brandService.add(brand);
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
    public Result getPage(Brand brand,
        @RequestParam(value = "pageNum", required = false, defaultValue = Constants.pageNum) int page,
        @RequestParam(value = "rows", required = false, defaultValue = Constants.pageSize) int rows) {
        JqGrid grid = new JqGrid();
        grid.pageNum = 1;
        grid.recordsNum = 200;
        grid.totalPage = 20;
        Row row1 = new Row();
        row1.id = "id1";
        row1.name="name1";
        row1.description="des1";
        row1.createTime = "2015";
        grid.rows.add(row1);
        Row row2 = new Row();
        row2.id = "id2";
        row2.name="name2";
        row2.description="des2";
        row2.createTime = "2015";
        grid.rows.add(row2);
//        return "gridModel({'total': '2','page': '1','records': '300','rows':[{id:'1',name:'test',description:'LuanMa1',createTime:'1998-01-01'},{id:'2',name:'test2',description:'LuanMa2',createTime:'2007-10-02'}]})";
        Result<JqGrid> res = new Result<JqGrid>();
        res.data = grid;
        res.msg = "成功了！";
        res.code = "success";
        return res;
    }
//    public AjaxResults<PageInfo<?>> getPage(Brand brand,
//        @RequestParam(value = "pageNum", required = false, defaultValue = Constants.pageNum) int page,
//        @RequestParam(value = "rows", required = false, defaultValue = Constants.pageSize) int rows) {
//        return new AjaxResults<PageInfo<?>>(brandService.getPage(page, rows, brand));
//    }

    @RequestMapping(value = "/brand/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Brand brand) {
        brandService.updateById(brand);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/brand/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        brandService.delById(id);
        return AjaxResults.success();
    }

}
