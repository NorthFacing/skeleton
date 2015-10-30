package com.bob.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.modules.user.model.User;
import com.bob.modules.user.model.UserVo;
import com.bob.modules.user.service.UserService;

/**
 * userController
 *
 * @author Bob
 * @Date 2015-7-25 13:22:46
 * @since v0.0.1
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public Result edit(@Validated User user) {
        userService.save(user);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping(value = "/user/getById", method = RequestMethod.GET)
    public Result getById(String id) {
        return Result.success(userService.getById(id));
    }

    @RequestMapping(value = "/user/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "/user/getList", method = RequestMethod.POST)
    public Result getList(UserVo userVo) {
        return Result.success(userService.getList(userVo));
    }

    @ResponseBody
    @RequestMapping(value = "/user/getPage", method = RequestMethod.POST)
    public Result getPage(UserVo userVo) {
        PageInfo<User> pageInfo = userService.getPage(userVo.getPage(), userVo.getRows(), userVo);
        return Result.success(pageInfo);
    }

}
