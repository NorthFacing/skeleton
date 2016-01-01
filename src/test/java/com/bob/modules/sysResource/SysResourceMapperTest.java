package com.bob.modules.sysResource;

import com.bob.core.base.BaseMapperTest;
import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.myBatis.EntityUtil;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        sysResource.setLevel(1);
        return sysResource;
    }

    @Test
    public void insertTest() {
        SysResource sysResource = getSysResource();
        sysResourceMapper.insert(sysResource);
    }

    @Test
    public void selectTest() {
        SysResource sysResource = getSysResource();
        sysResourceMapper.insert(sysResource);

        SysResource select = sysResourceMapper.select(sysResource);
//        Assert.assertNotNull(select);
        select = sysResourceMapper.select(new SysResource().setName("name"));
        Assert.assertNotNull(select);
        select = sysResourceMapper.select(new SysResource().setName("name2"));
        Assert.assertNull(select);
        select = sysResourceMapper.select(new SysResource().setLevel(1));
        Assert.assertNotNull(select);

    }

    @Test
    public void updateTest() {
        SysResource sysResource = getSysResource();
        sysResourceMapper.insert(sysResource);

        sysResource.setName("updateName");
        sysResourceMapper.update(sysResource);
    }

}
