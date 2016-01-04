package com.bob.modules.sysResource;

import com.bob.core.base.BaseServiceTest;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceVo;
import com.bob.modules.sysResource.service.SysResourceService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bob on 2016/1/3.
 */
public class SysResourceServiceTest extends BaseServiceTest {

    @Autowired
    private SysResourceService sysResourceService;

    private SysResource getSysResource() {
        SysResource sysResource = new SysResource();
        sysResource.setName("Test");
        return sysResource;
    }

    @Test
    public void selectTest() {
        SysResource sysResource = getSysResource();
        sysResourceService.insert(sysResource);

        SysResource param = new SysResource();
        param.setId(sysResource.getId());
        SysResource result = sysResourceService.select(param);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectListTest() {
        SysResource sysResource = getSysResource();
        sysResourceService.insert(sysResource);

        SysResource param = new SysResource();

        List<SysResource> result = sysResourceService.selectList(param);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);

    }
}
