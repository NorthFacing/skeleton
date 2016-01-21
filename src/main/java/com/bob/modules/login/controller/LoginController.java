package com.bob.modules.login.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.LocalDateTool;
import com.bob.modules.sysLoginLog.entity.SysLoginLog;
import com.bob.modules.sysLoginLog.service.SysLoginLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Bob on 2016/1/4.
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController {
  @Autowired
  private SysLoginLogService sysLoginLogService;

  @RequestMapping(value = "/login")
  public String login() {
    return "/login/login";
  }

  @RequestMapping(value = "/loginAction")
  public String loginAction(String username, String password, HttpServletRequest request) {
    try {
      if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
        request.setAttribute("error", "用户名或密码不能为空！");
        return "/login/login";
      }
      // 想要得到 SecurityUtils.getSubject()的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
      Subject user = SecurityUtils.getSubject();
      // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
      // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
      // 当以上认证成功后会向下执行,认证失败会抛出异常
      UsernamePasswordToken token = new UsernamePasswordToken(username, password);
      try {
        user.login(token);
      } catch (UnknownAccountException uae) {
        token.clear();
        request.setAttribute("error", "账号不存在，请联系管理员！");
        return "/login/login";
      } catch (LockedAccountException lae) {
        token.clear();
        request.setAttribute("error", "用户已经被锁定，请联系管理员！");
        return "/login/login";
      } catch (DisabledAccountException dae) {
        token.clear();
        request.setAttribute("error", "用户已经被删除，请联系管理员！");
        return "/login/login";
      } catch (ExcessiveAttemptsException e) {
        token.clear();
        request.setAttribute("error", "登录失败次数过多,锁定10分钟!");
        return "/login/login";
      } catch (AuthenticationException e) {
        token.clear();
        request.setAttribute("error", "用户或密码错误！");
        return "/login/login";
      }
      // 记录登陆操作记录
      saveLoginLog(username);

      request.removeAttribute("error");
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "登录异常，请联系管理员！");
      return "/login/login";
    }
    return "/login/loginSuc";
  }

  private void saveLoginLog(String username) {
    SysLoginLog loginLog = new SysLoginLog();
    Session session = SecurityUtils.getSubject().getSession();
    loginLog.setLoginTime(LocalDateTool.getNow());
    loginLog.setUserId((String) session.getAttribute("userSessionId"));
    loginLog.setUserName(username);
    loginLog.setLoginIp(session.getHost());
    sysLoginLogService.insert(loginLog);
  }

}
