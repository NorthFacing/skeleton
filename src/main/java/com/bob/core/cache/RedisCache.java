package com.bob.core.cache;

import java.util.Set;

public class RedisCache<V> implements Cache<V> {

    private RedisCacheService redisCacheService;
    private String cacheName;
    private int expried;

    public RedisCache() {
    }

    public RedisCache(RedisCacheService redisCacheService, String cacheName, int expried) {
        super();
        this.redisCacheService = redisCacheService;
        this.cacheName = cacheName;
        this.expried = expried;
    }

    @Override
    public String getName() {
        return cacheName;
    }

    public int getExpried() {
        return expried;
    }

    public void setExpried(int expried) {
        this.expried = expried;
    }

    public String getCacheName() {
        return cacheName;
    }

    @Override
    public V get(String key) {
        return redisCacheService.get(cacheName, key);
    }

    @Override
    public void set(String key, V value) {
        redisCacheService.set(cacheName, key, value, expried);
    }

    @Override
    public void set(String key, V value, int expried) {
        redisCacheService.set(cacheName, key, value, expried);
    }

    @Override
    public boolean del(String key) {
        return redisCacheService.del(cacheName, key);
    }

    @Override
    public void clear() {
        redisCacheService.clearCache(cacheName);
    }

    @Override
    public Set<String> keys() {
        return redisCacheService.keys(cacheName);
    }

}
