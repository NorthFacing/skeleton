package com.bob.core.utils.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * Created by Bob on 2016/9/13.
 *
 * @RUL https://github.com/alexxiyang/shiro-redis/pull/3
 */
public class MyWebSessionManager extends DefaultWebSessionManager {

  Logger log = LoggerFactory.getLogger(MyWebSessionManager.class);

  /**
   * 将session缓存至request，这样每次请求只需要访问一次redis
   * @覆写 DefaultSessionManager 中的方法
   * @param sessionKey
   * @return
   * @throws UnknownSessionException
   */
  @Override
  protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {

    Serializable sessionId = getSessionId(sessionKey);
    if (sessionId == null) {
      log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a " +
          "session could not be found.", sessionKey);
      return null;
    }

    // ***************Add By Goma****************
    ServletRequest request = null;
    if (sessionKey instanceof WebSessionKey) {
      request = ((WebSessionKey) sessionKey).getServletRequest();
    }
    if (request != null) {
      Object s = request.getAttribute(sessionId.toString());
      if (s != null) {
        return (Session) s;
      }
    }
    // ***************Add By Goma****************

    Session s = retrieveSessionFromDataSource(sessionId);

    if (s == null) {
      //session ID was provided, meaning one is expected to be found, but we couldn't find one:
      String msg = "Could not find session with ID [" + sessionId + "]";
      throw new UnknownSessionException(msg);
    }

    // ***************Add By Goma****************
    if (request != null) {
      request.setAttribute(sessionId.toString(), s);
    }
    // ***************Add By Goma****************

    return s;
  }
}
