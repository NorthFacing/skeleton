//package com.bob.core.cache.redis;
//
//import com.bob.core.cache.CacheService;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.Protocol;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.Set;
//
///**
// * Redis缓存实现：jedis实现
// */
//public class RedisCacheImpl implements CacheService {
//
//  private static JedisPool jedisPool = null;
//  private Logger logger = LoggerFactory.getLogger(RedisCacheImpl.class);
//  private String host;
//  private int port;
//  private String password;
//  private int timeout; // 连接超时时间
//  private JedisPoolConfig poolConfig;
//
//  public RedisCacheImpl() {
//  }
//
//  public static JedisPool getJedisPool() {
//    return jedisPool;
//  }
//
//  public static void setJedisPool(JedisPool jedisPool) {
//    RedisCacheImpl.jedisPool = jedisPool;
//  }
//
//  /**
//   * 初始化方法
//   * <p>
//   * PostConstruct 表示bean初始化之后调用此方法
//   */
//  @PostConstruct
//  public void init() {
//    logger.debug("RedisCache进行初始化");
//    timeout = (timeout == 0) ? Protocol.DEFAULT_TIMEOUT : timeout;
//    if (StringUtils.isNotEmpty(password)) {
//      jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
//    } else {
//      jedisPool = new JedisPool(poolConfig, host, port, timeout);
//    }
//  }
//
//  /**
//   * 销毁方法
//   * <p>
//   * PreDestroy 表示bean被销毁之前调用此方法
//   */
//  @PreDestroy
//  public void destroy() {
//    logger.debug("ShiroRedisImpl销毁jedisPool");
//    jedisPool.destroy();
//  }
//
//  @Override
//  public String get(String key) {
//    return this.get(null, key);
//  }
//
//  @Override
//  public String get(String prefix, String key) {
//    if (StringUtils.isBlank(key)) {
//      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//    }
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      String value = jedis.get(assembleKey(prefix, key));
//      if (null == value) {
//        return null;
//      }
//      logger.debug("[RedisCacheImpl] 从Redis获取对象：{} = {}", key, value);
//      return value;
//    } catch (Exception e) {
//      logger.error("从Redis获取对象时出错：{}", e.getMessage(), e);
//      return null;
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  @Override
//  public void set(String key, String value) {
//    this.set(null, key, value, 0);
//  }
//
//  @Override
//  public void set(String key, String value, int expried) {
//    this.set(null, key, value, expried);
//  }
//
//  @Override
//  public void set(String prefix, String key, String value) {
//    this.set(prefix, key, value, 0);
//  }
//
//  @Override
//  public void set(String prefix, String key, String value, int expired) {
//    if (StringUtils.isBlank(key)) {
//      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//    }
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      String assKey = assembleKey(prefix, key);
//      jedis.set(assKey, value);
//      if (expired != 0) {
//        jedis.expire(key, expired);
//      }
//      logger.debug("[RedisCacheImpl] 添加对象到Redis：{} = {}, expired = {}", key, value, expired);
//    } catch (Exception e) {
//      logger.error("添加对象到Redis出错：{}", e.getMessage(), e);
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  @Override
//  public boolean del(String key) {
//    return this.del(null, key);
//  }
//
//  @Override
//  public boolean del(String prefix, String key) {
//    if (StringUtils.isBlank(key)) {
//      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//    }
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      logger.debug("[RedisCacheImpl] 从Redis删除：key = {}", key);
//      return jedis.del(assembleKey(prefix, key)) > 0;
//    } catch (Exception e) {
//      logger.error("从Redis删除时出错：{}", e.getMessage(), e);
//      return false;
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  @Override
//  public Set<String> keys(String prefix) {
//    if (StringUtils.isBlank(prefix)) {
//      throw new IllegalArgumentException("The cache name：'" + prefix + "' is invalid.");
//    }
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      Set<String> keySet = jedis.keys(assembleKey(prefix, "*"));
//      return keySet;
//    } catch (Exception e) {
//      logger.error("byteKeys出错：{}", e.getMessage(), e);
//      return null;
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  @Override
//  public long clearCache(String prefix) {
//    if (StringUtils.isBlank(prefix)) {
//      throw new IllegalArgumentException("The cache name：'" + prefix + "' is invalid.");
//    }
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      Set<String> keySet = jedis.keys(assembleKey(prefix, "*"));
//      String[] keys = new String[keySet.size()];
//      int i = 0;
//      for (String key : keySet) {
//        keys[i++] = key;
//      }
//      return jedis.del(keys);
//    } catch (Exception e) {
//      logger.error("clearCache出错：{}", e.getMessage(), e);
//      return 0;
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  @Override
//  public void flushDB() {
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      jedis.flushDB();
//    } catch (Exception e) {
//      logger.error("flushDB出错：{}", e.getMessage(), e);
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  // *****************************************以下是setter和getter方法*****************************************
//
//  @Override
//  public Long dbSize() {
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      return jedis.dbSize();
//    } catch (Exception e) {
//      logger.error("dbSize出错：{}", e.getMessage(), e);
//      return 0L;
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
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
//  public JedisPoolConfig getPoolConfig() {
//    return poolConfig;
//  }
//
//  public void setPoolConfig(JedisPoolConfig poolConfig) {
//    this.poolConfig = poolConfig;
//  }
//
//  public String getHost() {
//    return host;
//  }
//
//  public void setHost(String host) {
//    this.host = host;
//  }
//
//  public int getPort() {
//    return port;
//  }
//
//  public void setPort(int port) {
//    this.port = port;
//  }
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  public int getTimeout() {
//    return timeout;
//  }
//
//  public void setTimeout(int timeout) {
//    this.timeout = timeout;
//  }
//}
