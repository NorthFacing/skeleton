package com.bob.biz.organization.controller;

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
import com.bob.biz.organization.model.Organization;
import com.bob.biz.organization.model.OrganizationVo;
import com.bob.biz.organization.service.OrganizationService;

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

    @ResponseBody
    @RequestMapping(value = "/organization/edit", method = RequestMethod.POST)
    public AjaxResults<?> edit(@Validated Organization organization) {
        organizationService.edit(organization);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Organization>(organizationService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getCodeByParentId", method = RequestMethod.GET)
    public AjaxResults<?> getCodeByParentId(String parentId) {
        return new AjaxResults<String>(organizationService.getCodeByParentId(parentId));
    }

    @RequestMapping(value = "/organization/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "organization/list";
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getPage", method = RequestMethod.POST)
    public AjaxResults<?> getPage(OrganizationVo organizationVo) {
        PageInfo<Organization> pageInfo = organizationService.getPage(organizationVo.getPage(),
            organizationVo.getRows(), organizationVo);
        return new AjaxResults<PageInfo<?>>(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getList", method = RequestMethod.POST)
    public AjaxResults<?> getList(OrganizationVo organizationVo) {
        List<Organization> list = organizationService.getList(organizationVo);
        return new AjaxResults<List<?>>(list);
    }

    @ResponseBody
    @RequestMapping(value = "/organization/delById", method = RequestMethod.POST)
    public AjaxResults<?> delById(String id) {
        organizationService.delById(id);
        return AjaxResults.success();
    }

}
