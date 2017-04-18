package com.bob.core.utils.shiro.shiroRedis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 使用ShiroCache替换shiro默认缓存实现
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(ShiroCache.class);

  private RedisTemplate<K, V> redisTemplate;

  @Override
  public V get(K key) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    V value = redisTemplate.opsForValue().get(assembleKey((String) key));
    logger.debug("[ShiroCache] 根据key从Redis中获取shiro对象： {} = {}", key, value);
    return value;
  }

  @Override
  public V put(K key, V value) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    redisTemplate.opsForValue().set((K) assembleKey((String) key), value);
    logger.debug("[ShiroCache] 添加shiro对象到redis： {} = {}", key, value);
    return value;
  }

  @Override
  public V remove(K key) throws CacheException {
    if (!(key instanceof String))
      throw new CacheException("[ShiroCache] key 值类型错误！");
    logger.debug("[ShiroCache] 从redis中删除shiro对象： key = {}", key);
    V value = redisTemplate.opsForValue().get(assembleKey((String) key));
    redisTemplate.delete(key);
    return value;
  }

  @Override
  public void clear() throws CacheException {
    logger.debug("[ShiroCache] 从redis中清空shiro所有对象");
    redisTemplate.delete((K) assembleKey("*"));
  }

  @Override
  public int size() {
    logger.debug("[ShiroCache] 获取dbSize");
    int size = redisTemplate.keys((K) assembleKey("*")).size();
    logger.debug("[ShiroCache] 获取dbSize：{}", size);
    return size;
  }

  @Override
  public Set<K> keys() {
    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys");
    Set<K> keys = redisTemplate.keys((K) assembleKey("*"));
    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys：共计 {} 个", keys.size());
    return keys;
  }

  @Override
  public Collection<V> values() {
    logger.debug("[ShiroCache] redis缓存shiro对象的values");
    Set<K> keys = redisTemplate.keys((K) assembleKey("*"));
    List<V> values = redisTemplate.opsForValue().multiGet(keys);
    logger.debug("[ShiroCache] redis缓存shiro对象的values：共计 {} 个", values.size());
    return values;
  }

  private String assembleKey(String key) {
    return new StringBuffer("shiro-").append(key).toString();
  }

}