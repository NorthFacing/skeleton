package com.bob.biz.demoKey.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.biz.demoKey.model.DemoKey;
import com.bob.core.base.BaseServiceTest;

public class DemoKeyServiceTest extends BaseServiceTest {

    @Autowired
    private DemoKeyService demoKeyService;

    @BeforeClass
    public static void startTest() {
        System.out.println("单元测试开始");
    }

    @Test
    public void testSave() {
        DemoKey demoKey = new DemoKey();
        demoKey.setBrand("三环");
        String id = demoKeyService.save(demoKey);
        Assert.assertNull("添加成功【demoKey ：" + id + "】", id);
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
    public static void endTest() {
        System.out.println("单元测试结束");
    }
}
