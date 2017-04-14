package com.bob.core.cacheTest;

import com.bob.core.base.BaseServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Bob on 2017/4/14.
 */
public class SpringRedisTemplateTest extends BaseServiceTest {

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private RedisTemplate<String, String> template;

  @Test
  public void M01_add() {
    template.opsForValue().set("foo", "bar");
  }

  @Test
  public void M02_get() {
    String foo = template.opsForValue().get("foo");
    Assert.assertTrue("bar".equals(foo));
  }

  @Test
  public void M03_del() {
    template.delete("foo");
  }

}
