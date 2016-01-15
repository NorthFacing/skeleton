package com.bob.core.cache.redis;

import com.bob.core.cache.Cache;
import com.bob.core.utils.javaUtil.SerializeUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis缓存实现：jedis实现
 */
public class ShiroRedisImpl implements Cache {

    private String host = "127.0.0.1";
    private int port = 6379;
    private String password = "";
    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private int timeout = 0;
    // 0 - never expire
    private int expired = 0;

    private String prefix = "";
    public static final String CONNECTOR = ":";
    public static final String DEFAULT_NAME_SPACE = "defNameSpace";

    private static JedisPool jedisPool = null;
    // 最大空闲连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数，
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private int MAX_IDLE = 100;
    // 最大连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
    private int MAX_TOTAL = 300;

    public ShiroRedisImpl() {
    }

    /**
     * 初始化方法
     */
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
        config.setMaxIdle(MAX_IDLE);
        // 最大连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
        config.setMaxTotal(MAX_TOTAL);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        if (jedisPool == null) {
            if (password != null && !"".equals(password)) {
                jedisPool = new JedisPool(config, host, port, timeout, password);
            } else if (timeout != 0) {
                jedisPool = new JedisPool(config, host, port, timeout);
            } else {
                jedisPool = new JedisPool(config, host, port);
            }
        }
    }

    /**
     * 销毁方法
     */
    public void destroy() {
        jedisPool.destroy();
    }


    @Override
    public Object get(String key) {
        return this.get(null, key);
    }

    @Override
    public Object get(String nameSpace, String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
        }
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            byte[] value = jedis.get(assembleKey(nameSpace, key));
            if (null == value) {
                return null;
            }
            return SerializeUtils.deserialize(value);
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return;
        }
        try {
            byte[] assKey = assembleKey(nameSpace, key);
            jedis.set(assKey, SerializeUtils.serialize(value));
            if (expired != 0) {
                jedis.expire(key, expired);
            }
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return false;
        }
        try {
            return jedis.del(assembleKey(nameSpace, key)) > 0;
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            Set<byte[]> keySet = jedis.keys(assembleKey(nameSpace, "*"));
            return keySet;
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return 0;
        }
        try {
            Set<byte[]> keySet = jedis.keys(assembleKey(nameSpace, "*"));
            byte[][] keys = new byte[keySet.size()][];
            int i = 0;
            for (byte[] key : keySet) {
                keys[i++] = key;
            }
            return jedis.del(keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    // *****************************************shiro使用的缓存方法开始*****************************************

    public void set(byte[] key, byte[] value) {
        this.set(key, value, expired);
    }

    public void set(byte[] key, byte[] value, int expired) {
        if (key == null || key.length < 1) {
            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
        }
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return;
        }
        try {
            jedis.set(key, value);
            if (expired != 0) {
                jedis.expire(key, expired);
            }
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            byte[] value = jedis.get(key);
            return value;
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
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return false;
        }
        try {
            return jedis.del(key) > 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    // *****************************************shiro使用的缓存方法结束*****************************************

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

    // *****************************************以下是setter和getter方法*****************************************

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

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
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
        ShiroRedisImpl.jedisPool = jedisPool;
    }
}
