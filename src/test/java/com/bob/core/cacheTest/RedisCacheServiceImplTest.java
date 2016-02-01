package com.bob.core.cacheTest;

import com.bob.core.base.BaseServiceTest;
import com.bob.core.cache.CacheService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bob on 2016/1/5.
 */
public class RedisCacheServiceImplTest extends BaseServiceTest {

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private CacheService cacheService;

  @Test
  public void setTest() {
    cacheService.set("Bob", "Bob");
  }

  @Test
  public void getTest() {
    cacheService.set("Bob", "Bob");
    String bob = cacheService.get("Bob");
    Assert.assertNotNull(bob);
    System.out.println(bob);
  }

  @Test
  public void delTest() {
    cacheService.set("Bob", "Bob");
    String bob = cacheService.get("Bob");
    Assert.assertNotNull(bob);
    System.out.println(bob);
    cacheService.del("Bob");
    bob = cacheService.get("Bob");
    Assert.assertNull(bob);
  }
}
