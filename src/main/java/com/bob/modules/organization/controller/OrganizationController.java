package com.bob.modules.organization.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.modules.organization.model.Organization;
import com.bob.modules.organization.model.OrganizationVo;
import com.bob.modules.organization.service.OrganizationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * organizationController
 *
 * @author Bob
 * @Date 2015-7-21 15:19:55
 * @since v0.0.1
 */
@Controller
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @ResponseBody
    @RequestMapping(value = "/organization/edit", method = RequestMethod.POST)
    public Result edit(@Validated Organization organization) {
        organizationService.save(organization);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getById", method = RequestMethod.GET)
    public Result getById(String id) {
        return Result.success(organizationService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getCodeByParentId", method = RequestMethod.GET)
    public Result getCodeByParentId(String parentId) {
        return Result.success(organizationService.getCodeByParentId(parentId));
    }

    @RequestMapping(value = "/organization/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "organization/list";
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getPage", method = RequestMethod.POST)
    public Result getPage(OrganizationVo organizationVo) {
        PageInfo<Organization> pageInfo = organizationService.getPage(organizationVo.getPage(),
                organizationVo.getRows(), organizationVo);
        return Result.success(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/organization/getList", method = RequestMethod.POST)
    public Result getList(OrganizationVo organizationVo) {
        List<Organization> list = organizationService.getList(organizationVo);
        return Result.success(list);
    }

    @ResponseBody
    @RequestMapping(value = "/organization/delById", method = RequestMethod.POST)
    public Result delById(String id) {
        organizationService.delById(id);
        return Result.success();
    }

}
