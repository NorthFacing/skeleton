package com.bob.core.cache.redis;

import com.bob.core.cache.CacheService;
import com.bob.core.contants.Constants;
import com.bob.core.utils.javaUtil.SerializeUtils;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;

/**
 * Redis缓存实现：jedis实现
 */
@Service("cacheService")
public class CacheRedisImpl implements CacheService {

  private Logger logger = LoggerFactory.getLogger(CacheRedisImpl.class);

  private String host;
  private int port;
  private String password;
  private int timeout; // 连接超时时间

  public static String prefix = Constants.PROJECT_NAME;
  public static final String CONNECTOR = ":";
  public static final String DEFAULT_NAME_SPACE = "defNameSpace";

  private static JedisPool jedisPool = null;

  private JedisPoolConfig poolConfig;

//  private final ConcurrentMap<String, CacheRedisImpl> caches = new ConcurrentHashMap<>();

  public CacheRedisImpl() {
  }

  /**
   * 初始化方法
   *
   * @Note PostConstruct 表示bean初始化之后调用此方法
   */
  @PostConstruct
  public void init() {
    logger.debug("ShiroRedisImpl进行初始化");
    if (jedisPool == null) {
      if (StringUtils.isNotEmpty(password)) {
        jedisPool = new JedisPool(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password);
      } else if (timeout != 0) {
        jedisPool = new JedisPool(poolConfig, host, port, timeout);
      } else {
        jedisPool = new JedisPool(poolConfig, host, port);
      }
    }
  }

  /**
   * 销毁方法
   *
   * @Note PreDestroy 表示bean被销毁之前调用此方法
   */
  @PreDestroy
  public void destroy() {
    logger.debug("ShiroRedisImpl销毁jedisPool");
    jedisPool.destroy();
  }


  @Override
  @SuppressWarnings("unchecked")
  public Object get(String key) {
    return this.get(null, key);
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object get(String nameSpace, String key) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      byte[] value = jedis.get(assembleKey(nameSpace, key));
      if (null == value) {
        return null;
      }
      return SerializeUtils.deserialize(value);
    } catch (Exception e) {
      logger.error("从Redis获取对象时出错：{}", e.getMessage(), e);
      return null;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public void set(String key, Object value) {
    this.set(null, key, value, 0);
  }

  @Override
  public void set(String key, Object value, int expried) {
    this.set(null, key, value, expried);
  }

  @Override
  public void set(String nameSpace, String key, Object value) {
    this.set(nameSpace, key, value, 0);
  }

  @Override
  public void set(String nameSpace, String key, Object value, int expired) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    if (value == null) {
      throw new IllegalArgumentException("The cache value：'" + value + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      byte[] assKey = assembleKey(nameSpace, key);
      jedis.set(assKey, SerializeUtils.serialize(value));
      if (expired != 0) {
        jedis.expire(key, expired);
      }
    } catch (Exception e) {
      logger.error("添加对象到Redis出错：{}", e.getMessage(), e);
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public boolean del(String key) {
    return this.del(null, key);
  }

  @Override
  public boolean del(String nameSpace, String key) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.del(assembleKey(nameSpace, key)) > 0;
    } catch (Exception e) {
      logger.error("从Redis删除时出错：{}", e.getMessage(), e);
      return false;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public Set<byte[]> byteKeys(String nameSpace) {
    if (StringUtils.isBlank(nameSpace)) {
      throw new IllegalArgumentException("The cache name：'" + nameSpace + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Set<byte[]> keySet = jedis.keys(assembleKey(nameSpace, "*"));
      return keySet;
    } catch (Exception e) {
      logger.error("byteKeys出错：{}", e.getMessage(), e);
      return null;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public Set<String> keys(String nameSpace) {
    Set<byte[]> keySet = this.byteKeys(nameSpace);
    HashSet<String> keys = new HashSet<>();
    for (byte[] key : keySet) {
      String strKey = (String) SerializationUtils.deserialize(key);
      keys.add(strKey);
    }
    return keys;
  }

  @Override
  public long clearCache(String nameSpace) {
    if (StringUtils.isBlank(nameSpace)) {
      throw new IllegalArgumentException("The cache name：'" + nameSpace + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Set<byte[]> keySet = jedis.keys(assembleKey(nameSpace, "*"));
      byte[][] keys = new byte[keySet.size()][];
      int i = 0;
      for (byte[] key : keySet) {
        keys[i++] = key;
      }
      return jedis.del(keys);
    } catch (Exception e) {
      logger.error("clearCache出错：{}", e.getMessage(), e);
      return 0;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public void flushDB() {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
    } catch (Exception e) {
      logger.error("flushDB出错：{}", e.getMessage(), e);
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  @Override
  public Long dbSize() {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.dbSize();
    } catch (Exception e) {
      logger.error("dbSize出错：{}", e.getMessage(), e);
      return 0L;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  private byte[] assembleKey(String nameSpace, String key) {
    StringBuilder builder = new StringBuilder();
    if (StringUtils.isNotBlank(prefix)) {
      builder.append(prefix).append(CONNECTOR);
    }
    if (StringUtils.isBlank(nameSpace)) {
      nameSpace = DEFAULT_NAME_SPACE;
    }
    builder.append(nameSpace).append(CONNECTOR);
    builder.append(key);
    return SerializationUtils.serialize(builder.toString());
  }

  // *****************************************shiro使用的缓存方法开始*****************************************

//  public void set(byte[] key, byte[] value) {
//    this.set(key, value, expired);
//  }

  public void set(byte[] key, byte[] value, int expired) {
    if (key == null || key.length < 1) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set(key, value);
      if (expired != 0) {
        jedis.expire(key, expired);
      }
    } catch (Exception e) {
      logger.error("添加对象到redis出错：{}", e.getMessage(), e);
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  public byte[] get(byte[] key) {
    if (key == null || key.length < 1) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      byte[] value = jedis.get(key);
      return value;
    } catch (Exception e) {
      logger.error("根据key从Redis中获取对象出错：{}", e.getMessage(), e);
      return null;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  public boolean del(byte[] key) {
    if (key == null || key.length < 1) {
      throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      boolean del = jedis.del(key) > 0;
      return del;
    } catch (Exception e) {
      logger.error("从redis中删除对象出错：{}", e.getMessage(), e);
      return false;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }


  // *****************************************shiro使用的缓存方法结束*****************************************


  // *****************************************以下是setter和getter方法*****************************************


  public JedisPoolConfig getPoolConfig() {
    return poolConfig;
  }

  public void setPoolConfig(JedisPoolConfig poolConfig) {
    this.poolConfig = poolConfig;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public static JedisPool getJedisPool() {
    return jedisPool;
  }

  public static void setJedisPool(JedisPool jedisPool) {
    CacheRedisImpl.jedisPool = jedisPool;
  }
}
