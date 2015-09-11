package com.bob.biz.demoKey.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.core.base.BaseServiceTest;

public class DemoKeyServiceTest extends BaseServiceTest {

    @Autowired
    private DemoKeyService demoKeyService;

    @Before
    public void startTest() {
        System.out.println("单元测试开始");
    }

    @Test
    public void testSave() {
        demoKeyService.save(null);
        Assert.assertTrue("", true);
    }

    @Test
    public void testAdd() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetById() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetVoById() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetList() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetVoList() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPage() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetVoPage() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateById() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelById() {
        fail("Not yet implemented");
    }

    @AfterClass
    public void endTest() {
        System.out.println("单元测试结束");
    }
}
