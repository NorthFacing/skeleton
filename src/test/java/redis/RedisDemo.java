package redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Bob on 2015/9/28.
 */
public class RedisDemo {

    public static void main(String[] args) {
        try {

//            String host = "56870cdf50d44bc6.m.cnhza.kvstore.aliyuncs.com";//控制台显示访问地址
            String host = "121.41.45.54";// 中转地址
            int port = 6380;
            String name = "56870cdf50d44bc6";
            String password = "Yuantu123";
            Jedis jedis = new Jedis(host, port);
            jedis.auth(name + ":" + password);//鉴权信息由用户名:密码拼接而成 instance_id:password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
