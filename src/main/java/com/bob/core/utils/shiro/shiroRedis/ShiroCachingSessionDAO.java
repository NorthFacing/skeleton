package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.utils.shiro.session.ShiroSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 保留类，当前并未使用
 * 一般配置了缓存管理器，所有的操作都会优先在缓存中执行。
 * 本类可以在缓存操作之外进行持久化操作，比如，缓存中找不到之后会执行本类中的方法从数据库读取。
 */
public class ShiroCachingSessionDAO extends CachingSessionDAO {

  private static final Logger logger = LoggerFactory.getLogger(ShiroCachingSessionDAO.class);

  @Override
  protected Serializable doCreate(Session session) {
    Serializable sessionId = super.generateSessionId(session);
    super.assignSessionId(session, sessionId);
    try {
//      插入数据库
      logger.info("创建 shiro session 完毕, id = {}, name = {}", session.getId(), session.getClass().getName());
    } catch (Exception e) {
      logger.error("创建 shiro session失败，{}", e.getMessage(), e);
    }
    return sessionId;
  }

  /**
   * 根据会话ID获取会话
   */
  @Override
  protected Session doReadSession(Serializable sessionId) {
    Session session = null;
    try {
//      从数据库查找
      if (session != null) {
        logger.info("读取 shiro session 完毕, id = {}, name = {}", session.getId(), session.getClass().getName());
      } else {
        logger.warn("读取 shiro session 失败");
      }
    } catch (Exception e) {
      logger.error("读取Session失败，{}", e.getMessage(), e);
    }
    return session;
  }

  /**
   * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
   */
  @Override
  protected void doUpdate(Session session) {
    logger.debug("开始更新 shiro session, id = {}", session.getId());
    if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
      return; //如果会话过期/停止 没必要再更新了
    }
    try {
      if (session instanceof ShiroSession) {
//        修改数据库
        logger.debug("更新 shiro session 完毕, id = {}, name = {}", session.getId(), session.getClass().getName());
      } else {
        logger.error("更新 shiro session 失败， id = {}, name = {}", session.getId(), session.getClass().getName());
      }
    } catch (Exception e) {
      logger.error("更新Session失败，id = {}, {}", session.getId(), e.getMessage(), e);
    }
  }

  /**
   * 删除会话；当会话过期/会话停止（如用户退出时）会调用
   */
  @Override
  public void doDelete(Session session) {
    try {
//      从数据库删除
      logger.debug("删除 shiro session 完毕, id = {}", session.getId());
    } catch (Exception e) {
      logger.error("删除Session失败，id = {}, {}", session.getId(), e.getMessage(), e);
    }
  }


}