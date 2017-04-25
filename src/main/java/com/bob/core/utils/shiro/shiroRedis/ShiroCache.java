package com.bob.core.utils.shiro.shiroRedis;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 使用redisCache替换shiro默认缓存实现
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(ShiroCache.class);

  private String shiroCacheName;
  private RedisTemplate<K, V> redisTemplate;

  public ShiroCache(String cacheName, RedisTemplate template) {
    this.shiroCacheName = cacheName;
    this.redisTemplate = template;
  }

  @Override
  public V get(K key) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    key = (K) assembleKey((String) key);
    V value = redisTemplate.opsForValue().get(key);
    logger.debug("[ShiroCache] 根据key从Redis中获取shiro对象： {} = {}", key, value);
    return value;
  }

  @Override
  public V put(K key, V value) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    key = (K) assembleKey((String) key);
    redisTemplate.opsForValue().set(key, value);
    logger.debug("[ShiroCache] 添加shiro对象到redis： {} = {}", key, value);
    return value;
  }

  @Override
  public V remove(K key) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    logger.debug("[ShiroCache] 从redis中删除shiro对象： key = {}", key);
    key = (K) assembleKey((String) key);
    V value = redisTemplate.opsForValue().get(key);
    redisTemplate.delete(key);
    return value;
  }

  @Override
  public void clear() throws CacheException {
    K key = (K) assembleKey("*");
    logger.debug("[ShiroCache] 从redis中清空shiro所有对象：key = {}", key);
    redisTemplate.delete(key);
  }

  @Override
  public int size() {
    logger.debug("[ShiroCache] 获取dbSize");
    K key = (K) assembleKey("*");
    int size = redisTemplate.keys(key).size();
    logger.debug("[ShiroCache] 获取dbSize：{}", size);
    return size;
  }

  @Override
  public Set<K> keys() {
    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys");
    K key = (K) assembleKey("*");
    Set<K> keys = redisTemplate.keys(key);
    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys：共计 {} 个", keys.size());
    return keys;
  }

  @Override
  public Collection<V> values() {
    logger.debug("[ShiroCache] redis缓存shiro对象的values");
    K key = (K) assembleKey("*");
    Set<K> keys = redisTemplate.keys(key);
    List<V> values = redisTemplate.opsForValue().multiGet(keys);
    logger.debug("[ShiroCache] redis缓存shiro对象的values：共计 {} 个", values.size());
    return values;
  }

  private String assembleKey(String key) {
    String cacheKey = new StringBuffer("shiro:")
        .append(shiroCacheName).append("-")
        .append(key).toString();
    return cacheKey;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}