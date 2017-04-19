package com.bob.core.utils.shiro.shiroRedis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 使用RedisCacheManager替换shiro默认缓存管理
 */
public class ShiroCacheManager implements CacheManager {

  private static final Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);

  /**
   * 此值由 spring 在 xml 中注入
   */
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    ShiroCache<K,V> shiroCache = new ShiroCache<>(name, redisTemplate);
    logger.debug("获取名称为: " + name + " 的 ShiroCache 实例：{}", shiroCache);
    return shiroCache;
  }

  public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }
}
