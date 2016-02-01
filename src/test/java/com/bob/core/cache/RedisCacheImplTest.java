package com.bob.core.cache;

import com.bob.core.base.BaseServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bob on 2016/1/5.
 */
public class RedisCacheImplTest extends BaseServiceTest {

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private Cache cache;

  @Test
  public void setTest() {
    cache.set("Bob", "Bob");
  }

  @Test
  public void getTest() {
    cache.set("Bob", "Bob");
    String bob = cache.get("Bob");
    Assert.assertNotNull(bob);
    System.out.println(bob);
  }

  @Test
  public void delTest() {
    cache.set("Bob", "Bob");
    String bob = cache.get("Bob");
    Assert.assertNotNull(bob);
    System.out.println(bob);
    cache.del("Bob");
    bob = cache.get("Bob");
    Assert.assertNull(bob);
  }
}
