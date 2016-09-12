package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.utils.javaUtil.SerializeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 使用RedisCache替换shiro默认缓存实现
 * TODO：存储的都是些什么内容？
 * TODO：hmset最大size是多少？数量特别大之后效率如何？
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(RedisCache.class);

  private static final byte[] hmsetKey = SerializeUtils.serialize("shiroRedisKey");

  private String host;
  private int port;
  private String password;
  private int timeout; // 连接超时时间
  private JedisPoolConfig poolConfig;
  private static JedisPool jedisPool;


  /**
   *
   */
  @PostConstruct
  public void init() {
    logger.debug("RedisCache进行初始化");
    timeout = (timeout == 0) ? Protocol.DEFAULT_TIMEOUT : timeout;
    if (StringUtils.isNotEmpty(password)) {
      jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
    } else {
      jedisPool = new JedisPool(poolConfig, host, port, timeout);
    }
  }

  @PreDestroy
  public void destroy() {
    logger.debug("ShiroRedisImpl销毁jedisPool");
    jedisPool.destroy();
  }

  //  public RedisCache() {
//  }
//
//  public RedisCache(String preKey) {
//  }

  @Override
  public V put(K key, V value) throws CacheException {
    logger.debug("[RedisCache] 添加shiro对象到redis： {} = {}", key, value);
    Jedis jedis = null;
    try {
      byte[] bKey = SerializeUtils.serialize(key);
      byte[] bValue = SerializeUtils.serialize(value);
      jedis = jedisPool.getResource();
      HashMap<byte[], byte[]> map = new HashMap<>();
      map.put(bKey, bValue);
      jedis.hmset(hmsetKey, map);
      return value;
    } catch (Throwable t) {
      logger.error("[RedisCache] 添加shiro对象到redis出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public V get(K key) throws CacheException {
    logger.debug("[RedisCache] 根据key从Redis中获取shiro对象： key = {}", key);
    if (key == null) {
      return null;
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      byte[] bKey = SerializeUtils.serialize(key);
      List<byte[]> hmget = jedis.hmget(hmsetKey, bKey);
      if (CollectionUtils.isEmpty(hmget))
        return null;

      byte[] bValue = hmget.get(0);
      V value = (V) SerializeUtils.deserialize(bValue);
      logger.debug("[RedisCache] 根据key从Redis中获取shiro对象： {} = {}", key, value);
      return value;
    } catch (Throwable t) {
      logger.error("[RedisCache] 根据key从Redis中获取shiro对象出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    }

  }

  @Override
  public V remove(K key) throws CacheException {
    logger.debug("[RedisCache] 从redis中删除shiro对象： key = {}", key);
    if (key == null) {
      return null;
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      V value = get(key);
      byte[] bKey = SerializeUtils.serialize(key);
      jedis.del(hmsetKey, bKey);
      return value;
    } catch (Throwable t) {
      logger.error("[RedisCache] 从redis中删除shiro对象出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    }
  }

  @Override
  public int size() {
    logger.debug("[RedisCache] 获取dbSize");
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      int size = jedis.hlen(hmsetKey).intValue();
      logger.debug("[RedisCache] 获取dbSize：{}", size);
      return size;
    } catch (Throwable t) {
      logger.error("[RedisCache] 获取dbSize出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    }
  }

  @Override
  public Set<K> keys() {
    logger.debug("[RedisCache] 从redis缓存shiro对象的keys");
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Set<byte[]> keys = jedis.hkeys(hmsetKey);
      Set<K> kKeys = new HashSet<>();
      keys.stream().forEach(key -> kKeys.add((K) SerializeUtils.deserialize(key)));
      logger.debug("[RedisCache] 从redis缓存shiro对象的keys：共计 {} 个", kKeys.size());
      return kKeys;
    } catch (Throwable t) {
      logger.error("[RedisCache] 获取dbSize出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    }
  }

  @Override
  public Collection<V> values() {
    logger.debug("[RedisCache] redis缓存shiro对象的values");
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Collection<byte[]> values = jedis.hvals(hmsetKey);
      Collection<V> vValues = new LinkedList<>();
      values.stream().forEach(value -> vValues.add((V) SerializeUtils.deserialize(value)));
      logger.debug("[RedisCache] redis缓存shiro对象的values：共计 {} 个", values.size());
      return vValues;
    } catch (Throwable t) {
      logger.error("[RedisCache] redis缓存shiro对象的values出错：{}", t.getMessage(), t);
      throw new CacheException(t);
    }

  }

  @Override
  public void clear() throws CacheException {
    logger.debug("[RedisCache] 从redis中删除所有shiro对象。");
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Set<byte[]> hkeys = jedis.hkeys(hmsetKey);
      byte[][] bhkeys = hkeys.toArray(new byte[hkeys.size()][]);
      jedis.hdel(hmsetKey, bhkeys);
    } catch (Throwable t) {
      throw new CacheException(t);
    }
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public void setPoolConfig(JedisPoolConfig poolConfig) {
    this.poolConfig = poolConfig;
  }
}
