//package com.bob.core.cache;
//
//import java.util.Set;
//
///**
// * 缓存服务接口定义
// */
//public interface CacheService {
//
//  /**
//   * <pre>
//   * 返回指定key的缓存值
//   * @return T 缓存数据
//   */
//  <T> T get(final String key);
//
//  /**
//   * <pre>
//   * 返回指定命名空间下指定key的缓存值
//   * @param cacheName 缓存所在命名空间
//   * @param key 缓存key
//   * @return T 缓存数据
//   */
//  <T> T get(final String cacheName, final String key);
//
//  /**
//   * <pre>
//   * 设置缓存
//   * @param key 缓存key
//   * @param value 缓存对象
//   */
//  void set(final String key, final String value);
//
//  /**
//   * <pre>
//   * 设置缓存,并指定缓存过期时间
//   * @param key 缓存key
//   * @param value 缓存对象
//   * @param expried 过期时间（单位：秒）
//   */
//  void set(final String key, final String value, final int expried);
//
//  /**
//   * <pre>
//   * 设置缓存到指定命名空间下
//   * @param cacheName 缓存所在命名空间
//   * @param key 缓存key
//   * @param value 缓存对象
//   */
//  void set(final String cacheName, final String key, final String value);
//
//  /**
//   * <pre>
//   * 设置缓存到指定命名空间下,并指定缓存过期时间
//   * @param cacheName 缓存所在命名空间
//   * @param key 缓存key
//   * @param value 缓存对象
//   * @param expried 过期时间（单位：秒）
//   */
//  void set(final String cacheName, final String key, final String value, final int expried);
//
//  /**
//   * <pre>
//   * 删除缓存
//   * @param key 缓存key
//   * @return 返回是否删除成功
//   */
//  boolean del(final String key);
//
//  /**
//   * <pre>
//   * 删除指定命名空间下的缓存
//   * @param cacheName 缓存所在命名空间
//   * @param key 缓存key
//   * @return boolean 返回是否删除成功
//   */
//  boolean del(final String cacheName, final String key);
//
//  /**
//   * <pre>
//   * 获取所有缓存的key
//   * @param cacheName
//   * @return int
//   */
//
//  Set<String> keys(final String cacheName);
//
//  /**
//   * <pre>
//   * 清除缓存
//   * @param cacheName
//   * @return int
//   */
//  long clearCache(final String cacheName);
//
//  void flushDB();
//
//  Long dbSize();
//}
