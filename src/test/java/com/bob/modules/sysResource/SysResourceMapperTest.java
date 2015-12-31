package com.bob.modules.sysResource;

import com.bob.core.base.BaseMapperTest;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

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

}
