package com.mall.biz.organization.controller;

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
import com.mall.biz.organization.model.Organization;
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

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Organization organization) {
        organizationService.add(organization);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Organization>(organizationService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(Organization organization) {
        return new AjaxResults<List<?>>(organizationService.getList(organization));
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(Organization organization,
        @RequestParam(defaultValue = Constants.pageNum) int pageNum,
        @RequestParam(defaultValue = Constants.pageSize) int pageSize) {
        return new AjaxResults<PageInfo<?>>(organizationService.getPage(pageNum, pageSize, organization));
    }

    @RequestMapping(value = "/organization/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated Organization organization) {
        organizationService.updateById(organization);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/organization/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        organizationService.delById(id);
        return AjaxResults.success();
    }

}
