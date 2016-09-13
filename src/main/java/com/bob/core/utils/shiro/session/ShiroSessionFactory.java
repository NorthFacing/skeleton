package com.bob.core.utils.shiro.session;


import com.bob.core.utils.http.IpUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

public class ShiroSessionFactory implements SessionFactory {

  @Override
  public Session createSession(SessionContext initData) {
    ShiroSession session = new ShiroSession();
    if (initData != null && initData instanceof WebSessionContext) {
      WebSessionContext sessionContext = (WebSessionContext) initData;
      HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
      if (request != null) {
        session.setHost(IpUtils.getIpAddr(request));
        session.setUserAgent(request.getHeader("User-Agent"));
      }
    }
    return session;
  }
}
