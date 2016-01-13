//package com.bob.core.cache.redis;
//
//import com.bob.core.cache.Cache;
//import com.bob.core.utils.javaUtil.SerializeUtils;
//import org.apache.commons.lang.SerializationUtils;
//import org.apache.commons.lang.StringUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.Set;
//
//public class CacheImpl implements Cache {
//
//
//    private static final String DEFAULT_CACHE_NAME = "_def";
//    private static final String CONNECTOR = ":";
//    private String prefix = "";
//
//    private String host = "127.0.0.1";
//
//    private int port = 6379;
//
//    // 0 - never expire
//    private int expired = 0;
//
//    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
//    private int timeout = 0;
//
//    private String password = "";
//
//    private JedisPoolConfig poolConfig;
//
//    private static JedisPool jedisPool = null;
//
//    public CacheImpl() {
//    }
//
//    public CacheImpl(JedisPoolConfig poolConfig, String host, int port, String password, int timeout) {
//        this.poolConfig = poolConfig;
//        this.host = host;
//        this.port = port;
//        this.password = password;
//        this.timeout = timeout;
//        init();
//    }
//
//    /**
//     * 初始化方法
//     */
//    public void init() {
//        if (jedisPool == null) {
//            if (password != null && !"".equals(password)) {
//                jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
//            } else if (timeout != 0) {
//                jedisPool = new JedisPool(poolConfig, host, port, timeout);
//            } else {
//                jedisPool = new JedisPool(poolConfig, host, port);
//            }
//
//        }
//    }
//
//    @Override
//    public int getDefaultExpried() {
//        return this.expired;
//    }
//
//    @Override
//    public void setDefaultExpried(int defaultExpried) {
//        this.expired = defaultExpried;
//    }
//
//    @Override
//    public Object get(String key) {
//        return this.get(null, key);
//    }
//
//    @Override
//    public Object get(String cacheName, String key) {
//        if (StringUtils.isBlank(key)) {
//            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//        }
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            return null;
//        }
//        try {
//            byte[] value = jedis.get(assembleKey(cacheName, key));
//            if (null == value) {
//                return null;
//            }
//            return SerializeUtils.deserialize(value);
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public void set(String key, Object value) {
//        this.set(null, key, value, 0);
//    }
//
//    @Override
//    public void set(String key, Object value, int expried) {
//        this.set(null, key, value, expried);
//    }
//
//    @Override
//    public void set(String cacheName, String key, Object value) {
//        this.set(cacheName, key, value, 0);
//    }
//
//    @Override
//    public void set(String cacheName, String key, Object value, int expired) {
//        if (StringUtils.isBlank(key)) {
//            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//        }
//        if (value == null) {
//            throw new IllegalArgumentException("The cache value：'" + value + "' is invalid.");
//        }
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            return;
//        }
//        try {
//            byte[] assKey = assembleKey(cacheName, key);
//            jedis.set(assKey, SerializeUtils.serialize(value));
//            if (expired != 0) {
//                jedis.expire(key, expired);
//            }
//
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean del(String key) {
//        return this.del(null, key);
//    }
//
//    @Override
//    public boolean del(String cacheName, String key) {
//        if (StringUtils.isBlank(key)) {
//            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
//        }
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            return false;
//        }
//        try {
//            return jedis.del(assembleKey(cacheName, key)) > 0;
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public Set<String> getKeys(String cacheName) {
//        if (StringUtils.isBlank(cacheName)) {
//            throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
//        }
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            return null;
//        }
//        try {
//            Set<byte[]> keySet = jedis.keys(assembleKey(cacheName, "*"));
//            return keySet;
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//
//    @Override
//    public long clearCache(String cacheName) {
//        if (StringUtils.isBlank(cacheName)) {
//            throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
//        }
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            return 0;
//        }
//        try {
//            Set<byte[]> keySet = this.getKeys(cacheName);
//            byte[][] keys = new byte[keySet.size()][];
//            int i = 0;
//            for (byte[] key : keySet) {
//                keys[i++] = key;
//            }
//            return jedis.del(keys);
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public void flushDB() {
//        Jedis jedis = jedisPool.getResource();
//        try {
//            jedis.flushDB();
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public Long dbSize() {
//        Long dbSize = 0L;
//        Jedis jedis = jedisPool.getResource();
//        try {
//            dbSize = jedis.dbSize();
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return dbSize;
//    }
//
//    private byte[] assembleKey(String key) {
//        return this.assembleKey(null, key);
//    }
//
//    private byte[] assembleKey(String cacheName, String key) {
//        StringBuilder builder = new StringBuilder();
//        if (StringUtils.isNotBlank(prefix)) {
//            builder.append(prefix).append(CONNECTOR);
//        }
//        if (StringUtils.isBlank(cacheName)) {
//            cacheName = DEFAULT_CACHE_NAME;
//        }
//        builder.append(cacheName).append(CONNECTOR);
//        builder.append(key);
//        return SerializationUtils.serialize(builder.toString());
//    }
//
//
//    public String getPrefix() {
//        return prefix;
//    }
//
//    public void setPrefix(String prefix) {
//        this.prefix = prefix;
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public int getExpired() {
//        return expired;
//    }
//
//    public void setExpired(int expired) {
//        this.expired = expired;
//    }
//
//    public int getTimeout() {
//        return timeout;
//    }
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public JedisPoolConfig getPoolConfig() {
//        return poolConfig;
//    }
//
//    public void setPoolConfig(JedisPoolConfig poolConfig) {
//        this.poolConfig = poolConfig;
//    }
//}
