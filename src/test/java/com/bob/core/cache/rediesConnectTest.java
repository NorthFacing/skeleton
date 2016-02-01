package com.bob.core.cache;

import com.bob.modules.sysUser.entity.SysUser;
import org.apache.commons.lang.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by Bob on 2016/1/4.
 */
public class rediesConnectTest {

  public static void main(String[] args) {

    JedisPoolConfig config = new JedisPoolConfig();
    // 最大空闲连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
    config.setMaxIdle(20);
    // 最大连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
    config.setMaxTotal(20);
    config.setTestOnBorrow(false);
    config.setTestOnReturn(false);

    String host = "192.168.31.128";

    Integer port = 6379;

    String password = "BobZhu";

    final JedisPool pool = new JedisPool(config, host, port, 3000, password);

    Jedis jedis = null;

    try {
      jedis = pool.getResource();

      /// ... do stuff here ... for example

      jedis.set("foo", "bar");
      String foobar = jedis.get("foo");
      System.out.println(foobar);
      jedis.zadd("sose", 0, "car");
      jedis.zadd("sose", 0, "bike");
      Set<String> sose = jedis.zrange("sose", 0, -1);
      for (String str : sose) {
        System.out.println(str);
      }

      SysUser user = new SysUser();
      user.setUserName("Bob");
      String key = "bob";
      try {
        jedis.set(key.getBytes(), SerializationUtils.serialize(user));
        jedis.expire(key.getBytes(), 100);
        byte[] value = jedis.get(key.getBytes());
        if (null == value) {
          return;
        }
        SysUser result = (SysUser) SerializationUtils.deserialize(value);
        System.out.println(result);

      } catch (Exception e) {
        e.printStackTrace();
      }

      boolean flag = jedis.isConnected();
      System.out.println(flag);

    } catch (Exception ee) {
      ee.printStackTrace();
    } finally {
      if (jedis != null) {
        boolean flag = jedis.isConnected();
        System.out.println(flag);

        jedis.close();

        flag = jedis.isConnected();
        System.out.println(flag);

      }
    }
  }
}
