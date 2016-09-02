package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.RedisCacheImpl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用RedisCache替换shiro默认缓存实现
 * TODO：存储的都是些什么内容？
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(RedisCache.class);

  private String prefix;
  private RedisCacheImpl redisCacheImpl;

  /**
   * 通过一个JedisManager实例构造RedisCache
   *
   * @param redisCacheImpl The cache manager instance
   */
  public RedisCache(RedisCacheImpl redisCacheImpl) {
    if (redisCacheImpl == null) {
      throw new IllegalArgumentException("Cache argument cannot be null.");
    }
    this.redisCacheImpl = redisCacheImpl;
  }

  /**
   * Constructs a cache instance with the specified
   * Redis manager and using a custom key nameSpace.
   *
   * @param redisCacheImpl The cache manager instance
   * @param prefix         The prefix of key
   */
  public RedisCache(RedisCacheImpl redisCacheImpl, String prefix) {
    this(redisCacheImpl);
    // set the prefix
    this.prefix = prefix;
  }

  @Override
  public V get(K key) throws CacheException {
    try {
      if (key == null) {
        return null;
      } else {
        V value = (V) redisCacheImpl.get(getKey(key));
        logger.debug("[RedisCache] 根据key从Redis中获取shiro对象： {} = {}", key, value);
        return value;
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }

  }

  @Override
  public V put(K key, V value) throws CacheException {
    logger.debug("[RedisCache] 添加shiro对象到redis： {} = {}", key, value);
    try {
      redisCacheImpl.set(getKey(key), (Serializable) value);
      return value;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public V remove(K key) throws CacheException {
    logger.debug("[RedisCache] 从redis中删除shiro对象： key = {}", key);
    try {
      V previous = get(key);
      redisCacheImpl.del(getKey(key));
      return previous;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public void clear() throws CacheException {
    logger.debug("[RedisCache] 从redis中删除所有shiro对象。");
    try {
      redisCacheImpl.clearCache(prefix);
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public int size() {
    try {
      Long longSize = redisCacheImpl.dbSize();
      int size = longSize.intValue();
      logger.debug("[RedisCache] 从redis获取shiro对象数量： size = {}", size);
      return size;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public Set<K> keys() {
    try {
      Set<String> keys = redisCacheImpl.keys(prefix);
      if (CollectionUtils.isEmpty(keys)) {
        return Collections.emptySet();
      } else {
        Set<K> newKeys = new HashSet<>();
        for (String key : keys) {
          newKeys.add((K) key);
        }
        logger.debug("[RedisCache] 从redis缓存shiro对象的keys：共计 {} 个", newKeys.size());
        return newKeys;
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public Collection<V> values() {
    try {
      Set<String> keys = redisCacheImpl.keys(prefix);
      if (!CollectionUtils.isEmpty(keys)) {
        List<V> values = new ArrayList<>(keys.size());
        for (String key : keys) {
          V value = get((K) key);
          if (value != null) {
            values.add(value);
          }
        }
        logger.debug("[RedisCache] 从redis缓存shiro对象的values：共计 {} 个", values.size());
        return Collections.unmodifiableList(values);
      } else {
        logger.debug("[RedisCache] 从redis缓存shiro对象的values：共计 0 个");
        return Collections.emptyList();
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  /**
   * 获取shiro的key
   *
   * @param key key
   * @return key拼装
   */
  private String getKey(K key) {
    if (key instanceof String) {
      return prefix + key;
    } else {
      return prefix + key.toString();
    }
  }
}
