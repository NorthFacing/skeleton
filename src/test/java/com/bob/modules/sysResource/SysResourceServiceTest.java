package com.bob.modules.sysResource;

import com.bob.core.base.BaseServiceTest;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bob on 2016/1/3.
 */
public class SysResourceServiceTest extends BaseServiceTest {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SysResourceMapper sysResourceMapper;

    private SysResource getSysResource() {
        SysResource sysResource = new SysResource();
        sysResource.setName("Test");
        return sysResource;
    }

    @Test
    public void selectTest() {
        SysResource sysResource = getSysResource();
        sysResourceMapper.insert(sysResource);

        SysResource param = new SysResource();
        param.setId(sysResource.getId());
        SysResource result = sysResourceMapper.select(param);
        Assert.assertNotNull(result);
    }

}
