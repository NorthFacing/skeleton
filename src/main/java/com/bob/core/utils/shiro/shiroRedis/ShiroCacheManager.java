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
public class ShiroCacheManager implements CacheManager {

  private static final Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);

  // fast lookup by name map
  private final ConcurrentMap<String, Cache> allCaches = new ConcurrentHashMap<>();

  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    Cache<K, V> cache = allCaches.get(name);
    if (cache == null) {
      cache = new ShiroCache<>();
      allCaches.put(name, cache);
    }
    logger.debug("获取名称为: " + name + " 的 ShiroCache 实例：{}", cache);
    return cache;
  }

}
