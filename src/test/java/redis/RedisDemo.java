package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Bob on 2016/8/31.
 */
public class RedisDemo {
  public static void main(String[] args) {

    String host = "127.0.0.1";
    int port = 6379;
    String pw = "adolphor.com";

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 10000, pw);
    Jedis jedis = jedisPool.getResource();
    jedis.set("foo","bar");
    String foo = jedis.get("foo");
    System.out.println(foo);
    jedis.del("foo");
  }
}
