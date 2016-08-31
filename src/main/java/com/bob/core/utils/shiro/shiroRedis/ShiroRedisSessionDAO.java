package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.CacheRedisImpl;
import com.bob.core.contants.BizConfig;
import com.bob.core.utils.javaUtil.SerializeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
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
 */
public class ShiroRedisSessionDAO extends CachingSessionDAO {

  private static Logger logger = LoggerFactory.getLogger(ShiroRedisSessionDAO.class);

  private String sessionNameSpace = "shiro_redis_session";

  private CacheRedisImpl redisImpl;

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
    byte[] sessionByte = redisImpl.get(getByteKey(sessionId));
    if (sessionByte == null) {
      return null;
    } else {
      return (Session) SerializeUtils.deserialize(sessionByte);
    }
  }

  /**
   * save session
   *
   * @param session
   * @throws UnknownSessionException
   */
  private void saveSession(Session session) throws UnknownSessionException {
    if (session == null || session.getId() == null) {
      logger.error("session or session id is null");
      return;
    }

    byte[] key = getByteKey(session.getId());
    byte[] value = SerializeUtils.serialize(session);
    int timeOut = BizConfig.sessionTimeOut * 60;
    session.setTimeout(timeOut * 1000);
    redisImpl.set(key, value, timeOut);
  }

  /**
   * 获得byte[]型的key
   *
   * @param sessionId
   * @return
   */
  private byte[] getByteKey(Serializable sessionId) {
    String preKey = this.sessionNameSpace + sessionId;
    return preKey.getBytes();
  }

  // 以下 setter & getter

  public CacheRedisImpl getCacheRedisImpl() {
    return redisImpl;
  }

  public void setShiroRedisImpl(CacheRedisImpl cacheRedisImpl) {
    this.redisImpl = cacheRedisImpl;
    // TODO 这里不需要初始化了吧
    // 初始化redisManager
//    this.redisImpl.init();
  }

  public void setNameSpace(String sessionNameSpace) {
    this.sessionNameSpace = sessionNameSpace;
  }

}
