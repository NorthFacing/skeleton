package com.bob.core.utils.shiro.shiroRedis;

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

  private RedisCache cache;

  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    logger.debug("获取名称为: " + name + " 的RedisCache实例");
    Cache c = caches.get(name);
    if (c == null) {
      // create a new cache instance
      c = cache;
      // add it to the cache collection
      caches.put(name, c);
    }
    return c;
  }

  public void setCache(RedisCache cache) {
    this.cache = cache;
  }
}
