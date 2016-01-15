package com.bob.core.cache.redis;

import com.bob.core.cache.Cache;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Redis缓存包装实现：spring实现（作为备份，未使用）
 */
public class RedisCacheImpl implements Cache {
    public static Logger logger = LoggerFactory.getLogger(RedisCacheImpl.class);
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
    public <T> T get(final String cacheName, final String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
        }
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
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
            }
        });
    }

    @Override
    public void set(final String key, final Object value) {
        set(null, key, value, expried);
    }

    @Override
    public void set(final String key, final Object value, final int expried) {
        set(null, key, value, expried);
    }

    @Override
    public void set(String cacheName, String key, Object value) {
        set(cacheName, key, value, expried);
    }

    @Override
    public void set(final String cacheName, final String key, final Object value, final int expried) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("The cache key：'" + key + "' is invalid.");
        }
        if (value == null) {
            throw new IllegalArgumentException("The cache value：'" + value + "' is invalid.");
        }
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] _key = assemblyKey(cacheName, key);
                byte[] _value = null;
                try {
                    if (!(value instanceof Serializable)) {
                        throw new IllegalArgumentException(RedisCacheImpl.class.getSimpleName() + " requires a Serializable payload "
                                + "but received an object of type [" + value.getClass().getName() + "]");
                    }
                    _value = SerializationUtils.serialize((Serializable) value);
                    connection.set(_key, _value);
                    if (expried > 0) {
                        connection.expire(_key, expried);
                    }
                } catch (Exception ex) {
                    logger.error("The cache object failed", ex);
                }
                return null;
            }
        });
    }

    @Override
    public boolean del(final String key) {
        return del(null, key);
    }

    @Override
    public boolean del(final String cacheName, final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(assemblyKey(cacheName, key)) > 0;
            }
        });
    }

    @Override
    public Set<String> keys(String cacheName) {
        Set<byte[]> _keyset = this.byteKeys(cacheName);
        Set<String> _keys = new HashSet<>();
        for (byte[] _key : _keyset) {
            _keys.add(redisTemplate.getStringSerializer().deserialize(_key));
        }
        return _keys;
    }

    @Override
    public Set<byte[]> byteKeys(String cacheName) {
        if (StringUtils.isBlank(cacheName)) {
            throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
        }
        return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] _key_pattern = redisTemplate.getStringSerializer().serialize(cacheName + CONNECTOR + "*");
                Set<byte[]> _keyset = connection.keys(_key_pattern);
                return _keyset;
            }
        });
    }

    @Override
    public long clearCache(final String cacheName) {
        if (StringUtils.isBlank(cacheName)) {
            throw new IllegalArgumentException("The cache name：'" + cacheName + "' is invalid.");
        }
        return redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
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
            }
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