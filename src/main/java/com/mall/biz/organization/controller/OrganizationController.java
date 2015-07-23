package com.mall.biz.organization.controller;

import java.util.List;

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
import com.mall.biz.organization.model.Organization;
import com.mall.biz.organization.model.OrganizationVo;
import com.mall.biz.organization.service.OrganizationService;

/**
 * organizationController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 15:19:55
 */
@Controller
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/org/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Organization organization) {
        organizationService.add(organization);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/org/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Organization>(organizationService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/org/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "org/list";
    }

    @ResponseBody
    @RequestMapping(value = "/org/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Organization organization) {
        return new AjaxResults<List<?>>(organizationService.getList(organization));
    }

//    @ResponseBody
//    @RequestMapping(value = "/org/getPage", method = RequestMethod.GET)
//    public AjaxResults<PageInfo<?>> getPage(OrganizationVo organizationVo) {
//        return new AjaxResults<PageInfo<?>>(organizationService.getPage(organizationVo));
//    }

    @RequestMapping(value = "/org/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Organization organization) {
        organizationService.updateById(organization);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/org/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        organizationService.delById(id);
        return AjaxResults.success();
    }

}
