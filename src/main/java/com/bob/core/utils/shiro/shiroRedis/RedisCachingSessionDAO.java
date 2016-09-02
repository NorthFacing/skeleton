package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.RedisCacheImpl;
import com.bob.core.contants.BizConfig;
import org.apache.commons.lang.SerializationUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 使用RedisSessionDAO替换shiro默认session管理
 *
 * @Note 继承AbstractSessionDAO实现的时候，doReadSession方法调用过于频繁，所以改为通过集成CachingSessionDAO来实现。
 * @Warn 注意，本地缓存通过EhCache实现，失效时间一定要远小于Redis失效时间，
 * 这样本地失效后，会访问Redis读取，并重新设置Redis上会话数据的过期时间。
 * <p>
 * TODO：为什么需要这个类？有什么作用？
 */
public class RedisCachingSessionDAO extends CachingSessionDAO {

  private static Logger logger = LoggerFactory.getLogger(RedisCachingSessionDAO.class);

  private String sessionPrefix = "shiro_redis_session"; // 作为默认值

  private RedisCacheImpl redisImpl;

  @Override
  protected void doUpdate(Session session) {
    if (session == null || session.getId() == null) {
      logger.error("session or session id is null");
      return;
    }
    if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
      return; //如果会话过期/停止 没必要再更新了
    }
    saveSession(session);
  }

  @Override
  protected void doDelete(Session session) {
    if (session == null || session.getId() == null) {
      logger.error("session or session id is null");
      return;
    }
    redisImpl.del(getByteKey(session.getId()));
  }

  @Override
  protected Serializable doCreate(Session session) {
    Serializable sessionId = super.generateSessionId(session);
    super.assignSessionId(session, sessionId);
    saveSession(session);
    return sessionId;
  }

  @Override
  protected Session doReadSession(Serializable sessionId) {
    if (sessionId == null) {
      logger.error("session id is null");
      return null;
    }
    Object s = redisImpl.get(getByteKey(sessionId));
    if (s == null) {
      return null;
    } else {
      return (Session) s;
    }
  }

  /**
   * save session
   *
   * @param session session
   * @throws UnknownSessionException UnknownSessionException
   */
  private void saveSession(Session session) throws UnknownSessionException {
    if (session == null || session.getId() == null) {
      logger.error("session or session id is null");
      return;
    }
    String key = getByteKey(session.getId());
    int timeOut = BizConfig.sessionTimeOut * 60;
    session.setTimeout(timeOut * 1000);
    redisImpl.set(key, (SimpleSession)session, timeOut);
  }

  /**
   * 获得byte[]型的key
   *
   * @param sessionId sessionId
   * @return
   */
  private String getByteKey(Serializable sessionId) {
    String preKey = this.sessionPrefix + sessionId;
    return preKey;
  }

  // 以下 setter 供shiro.xml配置调用

  public void setCacheRedisImpl(RedisCacheImpl cacheRedisImpl) {
    this.redisImpl = cacheRedisImpl;
  }

  public void setSessionPrefix(String sessionPrefix) {
    this.sessionPrefix = sessionPrefix;
  }

}
