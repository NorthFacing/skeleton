package com.bob.modules.weChat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bob.core.base.controller.BaseController;
import com.bob.core.contants.Config;
import com.bob.modules.weChat.utils.WxUtil;

/**
 * itemController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-8-3 13:53:31
 */
@Controller
public class WxController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(WxController.class);

    /*** =========================== 以下是首页 =========================== **/
    /** 首页 */
    @RequestMapping(value = "/item/home", method = { RequestMethod.GET })
    public void home(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.appId
                + "&redirect_uri=" + Config.wxUrl + "/cus/item/home.html"
                + "&response_type=code&scope=snsapi_base&state=getOpenId#wechat_redirect");
        } catch (IOException e) {
            LOG.error("获取openId出错：\n" + e);
        }
    }

    /** 首页重定向页 */
    @RequestMapping(value = "/item/home.html", method = { RequestMethod.GET })
    public String homePage(Model model, HttpServletRequest request, HttpServletResponse response) {
        boolean openId = WxUtil.checkOpenId(request);
        if (!openId) {
            openId = WxUtil.setOpenIdToCookie(request, response);
        }
        if (openId) {
            return "/item/home";
        } else {
            return "/item/getCodeError";
        }
    }

    /** 首页网页测试 */
    @RequestMapping(value = "/item/homeTest", method = { RequestMethod.GET })
    public String homeTest(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "/item/home";
    }

}
