package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.cache.redis.ShiroRedisImpl;
import com.bob.core.utils.javaUtil.SerializeUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用RedisCache替换shiro默认缓存实现
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(RedisCache.class);

  // The wrapped Jedis instance.
  private ShiroRedisImpl shiroRedisImpl;
  // The Redis key nameSpace for the sessions
  private String nameSpace;

  /**
   * 通过一个JedisManager实例构造RedisCache
   *
   * @param shiroRedisImpl The cache manager instance
   */
  public RedisCache(ShiroRedisImpl shiroRedisImpl) {
    if (shiroRedisImpl == null) {
      throw new IllegalArgumentException("Cache argument cannot be null.");
    }
    this.shiroRedisImpl = shiroRedisImpl;
  }

  /**
   * Constructs a cache instance with the specified
   * Redis manager and using a custom key nameSpace.
   *
   * @param shiroRedisImpl The cache manager instance
   * @param nameSpace      The Redis key nameSpace
   */
  public RedisCache(ShiroRedisImpl shiroRedisImpl, String nameSpace) {
    this(shiroRedisImpl);
    // set the nameSpace
    this.nameSpace = nameSpace;
  }

  @Override
  public V get(K key) throws CacheException {
    logger.debug("根据key从Redis中获取shiro对象： key [{}]", key);
    try {
      if (key == null) {
        return null;
      } else {
        byte[] rawValue = shiroRedisImpl.get(getByteKey(key));
        V value = (V) SerializeUtils.deserialize(rawValue);
        return value;
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }

  }

  @Override
  public V put(K key, V value) throws CacheException {
    logger.debug("添加shiro对象到redis： key [{}]", key);
    try {
      shiroRedisImpl.set(getByteKey(key), SerializeUtils.serialize(value));
      return value;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public V remove(K key) throws CacheException {
    logger.debug("从redis中删除shiro对象： key [{}]", key);
    try {
      V previous = get(key);
      shiroRedisImpl.del(getByteKey(key));
      return previous;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public void clear() throws CacheException {
    logger.debug("从redis中删除所有shiro对象。");
    try {
      shiroRedisImpl.clearCache(nameSpace);
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public int size() {
    try {
      Long longSize = new Long(shiroRedisImpl.dbSize());
      int size = longSize.intValue();
      logger.debug("从redis获取shiro对象数量： size [{}]", size);
      return size;
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public Set<K> keys() {
    try {
      Set<byte[]> keys = shiroRedisImpl.byteKeys(nameSpace);
      if (CollectionUtils.isEmpty(keys)) {
        return Collections.emptySet();
      } else {
        Set<K> newKeys = new HashSet<>();
        for (byte[] key : keys) {
          newKeys.add((K) key);
        }
        logger.debug("从redis缓存shiro对象的keys：共计 [{}] 个", newKeys.size());
        return newKeys;
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  @Override
  public Collection<V> values() {
    try {
      Set<byte[]> keys = shiroRedisImpl.byteKeys(nameSpace);
      if (!CollectionUtils.isEmpty(keys)) {
        List<V> values = new ArrayList<>(keys.size());
        for (byte[] key : keys) {
          V value = get((K) key);
          if (value != null) {
            values.add(value);
          }
        }
        logger.debug("从redis缓存shiro对象的values：共计 [{}] 个", values.size());
        return Collections.unmodifiableList(values);
      } else {
        logger.debug("从redis缓存shiro对象的values：共计 [0] 个");
        return Collections.emptyList();
      }
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  /**
   * 获得byte[]型的key
   *
   * @param key
   * @return
   */
  private byte[] getByteKey(K key) {
    if (key instanceof String) {
      String preKey = nameSpace + key;
      return preKey.getBytes();
    } else {
      return SerializeUtils.serialize(key);
    }
  }
}
