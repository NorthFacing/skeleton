package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.utils.shiro.session.ShiroSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * CachingSessionDAO 中已经进行了缓存，这里可以继续持久化数据库的操作，但并没有如此操作，
 * 保留此类的目的是以后如果想要持久化直接扩展此类即可。
 * 开涛：因为继承了 CachingSessionDAO；所有在读取时会先查缓存中是否存在，如果找不到才到数据库中查找。
 */
public class RedisCachingSessionDAO extends CachingSessionDAO {

  private static final Logger logger = LoggerFactory.getLogger(RedisCachingSessionDAO.class);

  /**
   * 如DefaultSessionManager在创建完session后会调用该方法
   */
  @Override
  protected Serializable doCreate(Session session) {
    // 创建一个Id并设置给Session
    Serializable sessionId = super.generateSessionId(session);
    logger.debug("开始创建 shiro session, id = {}", sessionId);
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
    logger.debug("开始读取 shiro session, id = {}", sessionId);
    Session session = null;
    try {
//      从数据库查找
      if (session != null) {
        logger.info("读取 shiro session 完毕, id = {}, name = {}", session.getId(), session.getClass().getName());
      } else {
        logger.warn("读取 shiro session 失败, id = {}", session.getId());
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
    if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
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
    logger.debug("开始删除 shiro session, id = {}", session.getId());
    try {
//      从数据库删除
      logger.debug("删除 shiro session 完毕, id = {}", session.getId());
    } catch (Exception e) {
      logger.error("删除Session失败，id = {}, {}", session.getId(), e.getMessage(), e);
    }
  }


}