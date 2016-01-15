package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.ShiroRedisImpl;
import com.bob.core.utils.javaUtil.SerializeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用RedisSessionDAO替换shiro默认session管理
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private ShiroRedisImpl shiroRedisImpl;

    private String keyPrefix = "shiro_redis_session:";

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
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
        session.setTimeout(shiroRedisImpl.getExpired() * 1000);
        this.shiroRedisImpl.set(key, value, shiroRedisImpl.getExpired());
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        shiroRedisImpl.del(this.getByteKey(session.getId()));

    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();

        Set<byte[]> keys = shiroRedisImpl.byteKeys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (byte[] key : keys) {
                Session s = (Session) SerializeUtils.deserialize(shiroRedisImpl.get(key));
                sessions.add(s);
            }
        }

        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }

        Session s = (Session) SerializeUtils.deserialize(shiroRedisImpl.get(this.getByteKey(sessionId)));
        return s;
    }

    /**
     * 获得byte[]型的key
     *
     * @param sessionId
     * @return
     */
    private byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    public ShiroRedisImpl getShiroRedisImpl() {
        return shiroRedisImpl;
    }

    public void setShiroRedisImpl(ShiroRedisImpl shiroRedisImpl) {
        this.shiroRedisImpl = shiroRedisImpl;

        /**
         * 初始化redisManager
         */
        this.shiroRedisImpl.init();
    }

    /**
     * Returns the Redis session keys
     * prefix.
     *
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key
     * prefix.
     *
     * @param keyPrefix The prefix
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

}
