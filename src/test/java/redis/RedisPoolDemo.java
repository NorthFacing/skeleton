package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by Bob on 2015/9/28.
 */
public class RedisPoolDemo {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
        config.setMaxIdle(200);
        //最大连接数, 应用自己评估，不要超过KVStore每个实例最大的连接数
        config.setMaxTotal(300);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        String host = "121.41.45.54";
        int port = 6380;
        String password = "56870cdf50d44bc6:Yuantu123";
        int timeout = 3000;
        JedisPool pool = new JedisPool(config, host, port, timeout, password);
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        /// ... when closing your application:
        pool.destroy();
    }
}
