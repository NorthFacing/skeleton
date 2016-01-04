package com.bob.modules.login.controller;

import com.bob.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bob on 2016/1/4.
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @RequestMapping(value = "/login")
    public String login(){
        return "/login/login";
    }

}
