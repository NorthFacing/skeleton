package com.bob.core.utils.shiro.shiroRedis;

import com.bob.core.utils.shiro.session.ShiroSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * 使用ShiroCache替换shiro默认缓存实现
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

  private Logger logger = LoggerFactory.getLogger(ShiroCache.class);

  @Autowired
  private RedisTemplate<String, byte[]> redisTemplate;

  @Override
  public V get(K key) throws CacheException {
    logger.debug("[ShiroCache] 根据key从Redis中获取shiro对象： key = {}", key);
    return null;
  }

  @Override
  public V put(K key, V value) throws CacheException {
    logger.debug("[ShiroCache] 添加shiro对象到redis： {} = {}", key, value);
    return null;
  }

  @Override
  public V remove(K key) throws CacheException {
    logger.debug("[ShiroCache] 从redis中删除shiro对象： key = {}", key);
    return null;
  }

  @Override
  public void clear() throws CacheException {
    logger.debug("[ShiroCache] 从redis中清空shiro所有对象");
  }

  @Override
  public int size() {
    logger.debug("[ShiroCache] 获取dbSize");
//    logger.debug("[ShiroCache] 获取dbSize：{}", size);
    return 0;
  }

  @Override
  public Set<K> keys() {
    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys");
//    logger.debug("[ShiroCache] 从redis缓存shiro对象的keys：共计 {} 个", kKeys.size());
    return null;
  }

  @Override
  public Collection<V> values() {
    logger.debug("[ShiroCache] redis缓存shiro对象的values");
//    logger.debug("[ShiroCache] redis缓存shiro对象的values：共计 {} 个", values.size());
    return null;
  }

}

@WritingConverter
class ShiroSessionToBytesConverter implements Converter<ShiroSession, byte[]> {

  private final Jackson2JsonRedisSerializer<ShiroSession> serializer;

  public ShiroSessionToBytesConverter() {
    serializer = new Jackson2JsonRedisSerializer<>(ShiroSession.class);
    serializer.setObjectMapper(new ObjectMapper());
  }

  @Override
  public byte[] convert(ShiroSession value) {
    return serializer.serialize(value);
  }
}

@ReadingConverter
class BytesToShiroSessionConverter implements Converter<byte[], ShiroSession> {

  private final Jackson2JsonRedisSerializer<ShiroSession> serializer;

  public BytesToShiroSessionConverter() {
    serializer = new Jackson2JsonRedisSerializer<>(ShiroSession.class);
    serializer.setObjectMapper(new ObjectMapper());
  }

  @Override
  public ShiroSession convert(byte[] value) {
    return serializer.deserialize(value);
  }
}