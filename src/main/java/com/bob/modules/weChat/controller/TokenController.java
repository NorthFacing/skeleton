package com.bob.modules.weChat.controller;

import com.bob.modules.weChat.service.TokenService;
import com.bob.modules.weChat.vo.CheckModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/wechat")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  /**
   * 开发者模式token校验
   *
   * @param wxAccount  开发者url后缀
   * @param response
   * @param tokenModel
   * @throws ParseException
   * @throws IOException
   */
  @RequestMapping(value = "/check/{wxToken}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain")
  public
  @ResponseBody
  String validate(@PathVariable("wxToken") String wxToken, CheckModel tokenModel)
      throws ParseException, IOException {
    return tokenService.validate(wxToken, tokenModel);
  }
}