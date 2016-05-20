package com.bob.modules.sysResource;

import com.bob.core.base.BaseMapperTest;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceQuery;
import com.bob.modules.sysResource.entity.SysResourceVo;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bob on 2015/12/31.
 */
public class SysResourceMapperTest extends BaseMapperTest {

  @Autowired
  @SuppressWarnings("SpringJavaAutowiringInspection")
  private SysResourceMapper sysResourceMapper;

  private SysResource getSysResource() {
    SysResource sysResource = new SysResource();
    sysResource.setName("name");
    sysResource.setResLevel(1);
    return sysResource;
  }

  @Test
  public void selectTest() {
    SysResource sysResource = getSysResource();
    sysResourceMapper.insert(sysResource);

    SysResource param1 = new SysResource();
    param1.setId(sysResource.getId());

    SysResource result1 = sysResourceMapper.select(param1);
    Assert.assertNotNull(result1);

    SysResourceVo result2 = sysResourceMapper.selectVo(param1);
    Assert.assertNotNull(result2);

  }

  @Test
  public void selectListTest() {
    SysResource sysResource = getSysResource();
    sysResourceMapper.insert(sysResource);

    SysResource param = new SysResource();

    List<SysResource> result3 = sysResourceMapper.selectList(param);
    Assert.assertNotNull(result3);
    Assert.assertTrue(result3.size() > 0);

    List<SysResourceVo> result4 = sysResourceMapper.selectVoList(param);
    Assert.assertNotNull(result4);
    Assert.assertTrue(result4.size() > 0);
  }

  @Test
  public void queryTest() {
    SysResource sysResource = getSysResource();
    sysResourceMapper.insert(sysResource);

    SysResourceQuery sysResourceQuery = new SysResourceQuery();

    Long count = sysResourceMapper.count(sysResourceQuery);
    Assert.assertTrue(count > 0);

    sysResourceQuery.setTotalCount(count);

    List<SysResource> result = sysResourceMapper.query(sysResourceQuery);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.size() > 0);
  }

}
