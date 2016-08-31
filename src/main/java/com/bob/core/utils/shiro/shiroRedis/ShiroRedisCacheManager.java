package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.CacheRedisImpl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 使用RedisCacheManager替换shiro默认缓存管理
 */
public class ShiroRedisCacheManager implements CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

    // fast lookup by name map
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    // The Redis key prefix for caches
    private String nameSpace = "shiro_redis_cache:";
    // 缓存实例
    private CacheRedisImpl shiroRedisImpl;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = caches.get(name);
        if (c == null) {
            // initialize the Redis manager instance
            shiroRedisImpl.init();
            // create a new cache instance
            c = new ShiroRedisCache<K, V>(shiroRedisImpl, nameSpace);
            // add it to the cache collection
            caches.put(name, c);
        }
        return c;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public CacheRedisImpl getShiroRedisImpl() {
        return shiroRedisImpl;
    }

    public void setShiroRedisImpl(CacheRedisImpl shiroRedisImpl) {
        this.shiroRedisImpl = shiroRedisImpl;
    }

}
