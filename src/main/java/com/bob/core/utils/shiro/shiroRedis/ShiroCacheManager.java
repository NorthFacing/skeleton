package com.bob.core.utils.shiro.shiroRedis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用RedisCacheManager替换shiro默认缓存管理
 */
public class ShiroCacheManager implements CacheManager {

  private static final Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);

  private ShiroCache shiroCache;

  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    logger.debug("获取名称为: " + name + " 的 ShiroCache 实例：{}", shiroCache);
    return shiroCache;
  }

  public void setShiroCache(ShiroCache shiroCache) {
    this.shiroCache = shiroCache;
  }
}
