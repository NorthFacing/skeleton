package com.mall.biz.user.controller;

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
import com.mall.biz.user.model.User;
import com.mall.biz.user.model.UserVo;
import com.mall.biz.user.service.UserService;

/**
 * userController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-20 18:29:10
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated User user) {
        userService.add(user);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/user/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<User>(userService.getById(id));
    }

    @RequestMapping(value = "/user/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "/user/getList", method = RequestMethod.GET)
    public AjaxResults<List<?>> getList(User user) {
        return new AjaxResults<List<?>>(userService.getList(user));
    }

    @ResponseBody
    @RequestMapping(value = "/user/getPage", method = RequestMethod.GET)
    public AjaxResults<PageInfo<?>> getPage(UserVo userVo) {
        return new AjaxResults<PageInfo<?>>(userService.getPage(userVo));
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public AjaxResults<?> update(@Validated User user) {
        userService.updateById(user);
        return AjaxResults.success();
    }

    @RequestMapping(value = "/user/delById", method = RequestMethod.GET)
    public AjaxResults<?> delById(String id) {
        userService.delById(id);
        return AjaxResults.success();
    }

}
