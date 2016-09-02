package com.bob.core.cache.redis;

import com.bob.core.cache.CacheService;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Redis缓存包装实现：spring实现（作为备份，未使用）
 */
@Deprecated
//@Service("cacheService")
public class RedisCacheServiceImpl implements CacheService {
  public static Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
  private static final String DEFAULT_CACHE_NAME = "_def";
  private static final String CONNECTOR = ":";
  private String prefix = "";
  private int expried = 0;                                                // 默认过期时间（0为永不过期）
  private RedisTemplate<Serializable, Serializable> redisTemplate;

  public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    if (!StringUtils.isBlank(prefix)) {
      this.prefix = StringUtils.trim(prefix);
    }
  }

  @Override
  public <T> T get(final String key) {
    return get(null, key);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(final String cacheName, final String key) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    return redisTemplate.execute((RedisConnection connection) -> {
      byte[] _key = assemblyKey(cacheName, key);
      if (connection.exists(_key)) {
        byte[] _value = connection.get(_key);
        try {
          return (T) SerializationUtils.deserialize(new ByteArrayInputStream(_value));
        } catch (Exception ex) {
          logger.error("Read cache object error", ex);
        }
      }
      return null;
    });
  }

  @Override
  public void set(final String key, final Serializable value) {
    set(null, key, value, expried);
  }

  @Override
  public void set(final String key, final Serializable value, final int expried) {
    set(null, key, value, expried);
  }

  @Override
  public void set(String cacheName, String key, Serializable value) {
    set(cacheName, key, value, expried);
  }

  @Override
  public void set(final String cacheName, final String key, final Serializable value, final int expried) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    redisTemplate.execute((RedisConnection connection) -> {
      byte[] _key = assemblyKey(cacheName, key);
      byte[] _value = null;
      try {
        if (!(value instanceof Serializable)) {
          throw new IllegalArgumentException(RedisCacheServiceImpl.class.getSimpleName() + " requires a Serializable payload "
                  + "but received an object of type [" + value.getClass().getName() + "]");
        }
        _value = SerializationUtils.serialize(value);
        connection.set(_key, _value);
        if (expried > 0) {
          connection.expire(_key, expried);
        }
      } catch (Exception ex) {
        logger.error("The cache object failed", ex);
      }
      return null;
    });
  }

  @Override
  public boolean del(final String key) {
    return del(null, key);
  }

  @Override
  public boolean del(final String cacheName, final String key) {
    return redisTemplate.execute(
            (RedisConnection connection) ->
                    (connection.del(assemblyKey(cacheName, key)) > 0)
    );
  }

  @Override
  public Set<String> keys(String cacheName) {
    if (StringUtils.isBlank(cacheName)) {
      throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
    }
    return redisTemplate.execute((RedisConnection connection) -> {
              byte[] _key_pattern = redisTemplate.getStringSerializer().serialize(cacheName + CONNECTOR + "*");
              Set<byte[]> _keyset = connection.keys(_key_pattern);
              Set<String> keys = new HashSet<>();
              for (byte[] _key : _keyset)
                keys.add(new String(_key));
              return keys;
            }
    );
  }

  @Override
  public long clearCache(final String cacheName) {
    if (StringUtils.isBlank(cacheName)) {
      throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
    }
    return redisTemplate.execute((RedisConnection connection) -> {
      StringBuilder builder = new StringBuilder();
      if (StringUtils.isNotBlank(prefix)) {
        builder.append(prefix).append(CONNECTOR);
      }
      builder.append(cacheName).append(CONNECTOR).append("*");

      byte[] _key_pattern = redisTemplate.getStringSerializer().serialize(builder.toString());
      Set<byte[]> _keyset = connection.keys(_key_pattern);
      byte[][] _keys = new byte[_keyset.size()][];
      int i = 0;
      for (byte[] _key : _keyset) {
        _keys[i++] = _key;
      }
      return connection.del(_keys).intValue();
    });
  }

  @Override
  public void flushDB() {
    try {
      throw new Exception("Todo impl");
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }

  @Override
  public Long dbSize() {
    try {
      throw new Exception("Todo impl");
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  private byte[] assemblyKey(String cacheName, String key) {
    StringBuilder builder = new StringBuilder();
    if (StringUtils.isNotBlank(prefix)) {
      builder.append(prefix).append(CONNECTOR);
    }
    if (StringUtils.isBlank(cacheName)) {
      cacheName = DEFAULT_CACHE_NAME;
    }
    builder.append(cacheName).append(CONNECTOR);
    builder.append(key);
    return redisTemplate.getStringSerializer().serialize(builder.toString());
  }
}
