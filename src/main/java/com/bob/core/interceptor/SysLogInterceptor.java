package com.bob.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bob.core.contants.Constants;
import com.bob.core.utils.ApplicationContextUtil;
import com.bob.core.utils.session.UserSession;
import com.bob.core.utils.web.RequestContext;
import com.bob.modules.sysLog.entity.SysLog;
import com.bob.modules.sysLog.service.SysLogService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 自动注入bean 验证规则拦截器
 */
public class SysLogInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {

    String contextPath = request.getContextPath();
    String requestURI = request.getRequestURI();
    requestURI = requestURI.substring(contextPath.length(), requestURI.length());
    requestURI = requestURI.split("\\?")[0];

    if (Constants.logMap.containsKey(requestURI)) {
      SysLogService logService = (SysLogService) ApplicationContextUtil.getContext().getBean("sysLogServiceImpl");
      UserSession loginUser = RequestContext.getLoginUser();
      SysLog sysLog = new SysLog();
      JSONObject jsonObject = Constants.logMap.get(requestURI);
      sysLog.setModule(jsonObject.getString("module"));
      sysLog.setOprType(jsonObject.getString("oprType"));
      sysLog.setOprName(jsonObject.getString("oprName"));
      sysLog.setUserName(loginUser.getLoginName());
      sysLog.setCorpId(loginUser.getCorpId());
      sysLog.setUserId(loginUser.getUserId());
      sysLog.setUrl(request.getRequestURI());
      sysLog.setOprTime(LocalDateTime.now());

      String idName = jsonObject.getString("idName");
      idName = (idName == null) ? "id" : idName;

      sysLog.setDataId(request.getParameter(idName));
      sysLog.setInParams(JSON.toJSONString(request.getParameterMap()));
      sysLog.setCreateTime(LocalDateTime.now());
      logService.insert(sysLog);
    }
  }

}
