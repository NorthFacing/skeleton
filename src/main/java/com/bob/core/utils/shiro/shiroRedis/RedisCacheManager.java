package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.RedisCacheImpl;
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
public class RedisCacheManager implements CacheManager {

  private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

  // fast lookup by name map
  private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

  // The Redis key prefix for caches
  private String prefix = "shiro_redis_cache:"; // 默认值
  // 缓存实例，由xml中spring bean注入
  private RedisCacheImpl redisCacheImpl;

  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    logger.debug("获取名称为: " + name + " 的RedisCache实例");
    Cache c = caches.get(name);
    if (c == null) {
      // create a new cache instance
      c = new RedisCache<K, V>(redisCacheImpl, prefix);
      // add it to the cache collection
      caches.put(name, c);
    }
    return c;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setRedisCacheImpl(RedisCacheImpl redisCacheImpl) {
    this.redisCacheImpl = redisCacheImpl;
  }

}
