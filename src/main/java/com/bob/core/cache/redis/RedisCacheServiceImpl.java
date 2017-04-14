//package com.bob.core.cache.redis;
//
//import com.bob.core.cache.CacheService;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Redis缓存包装实现：spring实现（作为备份，未使用）
// */
//public class RedisCacheServiceImpl implements CacheService {
//  public static Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
//  private int expried = 0;                                                // 默认过期时间（0为永不过期）
//  private RedisTemplate<String, String> redisTemplate;
//
//  public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
//    this.redisTemplate = redisTemplate;
//  }
//
//  @Override
//  public String get(final String key) {
//    return get(null, key);
//  }
//
//  @Override
//  public String get(final String cacheName, final String key) {
//    if (StringUtils.isBlank(key)) {
//      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//    }
//    return redisTemplate.opsForValue().get(assembleKey(cacheName, key));
//  }
//
//  @Override
//  public void set(final String key, final String value) {
//    set(null, key, value, expried);
//  }
//
//  @Override
//  public void set(final String key, final String value, final int expried) {
//    set(null, key, value, expried);
//  }
//
//  @Override
//  public void set(final String cacheName, final String key, final String value) {
//    set(cacheName, key, value, expried);
//  }
//
//  @Override
//  public void set(final String cacheName, final String key, final String value, final int expried) {
//    if (StringUtils.isBlank(key)) {
//      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//    }
//    redisTemplate.opsForValue().set(assembleKey(cacheName, key), value, expried);
//  }
//
//  @Override
//  public boolean del(final String key) {
//    return del(null, key);
//  }
//
//  @Override
//  public boolean del(final String cacheName, final String key) {
//    redisTemplate.delete(assembleKey(cacheName,key));
//  }
//
//  @Override
//  public Set<String> keys(String cacheName) {
//    if (StringUtils.isBlank(cacheName)) {
//      throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
//    }
//    return redisTemplate.execute((RedisConnection connection) -> {
//        byte[] _key_pattern = redisTemplate.getStringSerializer().serialize(cacheName + CONNECTOR + "*");
//        Set<byte[]> _keyset = connection.keys(_key_pattern);
//        Set<String> keys = new HashSet<>();
//        for (byte[] _key : _keyset)
//          keys.add(new String(_key));
//        return keys;
//      }
//    );
//  }
//
//  @Override
//  public long clearCache(final String cacheName) {
//    if (StringUtils.isBlank(cacheName)) {
//      throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
//    }
//    return redisTemplate.execute((RedisConnection connection) -> {
//      StringBuilder builder = new StringBuilder();
//      if (StringUtils.isNotBlank(prefix)) {
//        builder.append(prefix).append(CONNECTOR);
//      }
//      builder.append(cacheName).append(CONNECTOR).append("*");
//
//      byte[] _key_pattern = redisTemplate.getStringSerializer().serialize(builder.toString());
//      Set<byte[]> _keyset = connection.keys(_key_pattern);
//      byte[][] _keys = new byte[_keyset.size()][];
//      int i = 0;
//      for (byte[] _key : _keyset) {
//        _keys[i++] = _key;
//      }
//      return connection.del(_keys).intValue();
//    });
//  }
//
//  @Override
//  public void flushDB() {
//    try {
//      throw new Exception("Todo impl");
//    } catch (Exception e) {
//      logger.error(e.getMessage(), e);
//    }
//  }
//
//  @Override
//  public Long dbSize() {
//    try {
//      throw new Exception("Todo impl");
//    } catch (Exception e) {
//      logger.error(e.getMessage(), e);
//    }
//    return null;
//  }
//
//  private String assembleKey(String prefix, String key) {
//    StringBuilder builder = new StringBuilder();
//    if (StringUtils.isNotEmpty(prefix))
//      builder.append(prefix).append(":");
//    builder.append(key);
//    return builder.toString();
//  }
//
//}
