package com.bob.biz.demoKey.service;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.biz.demoKey.model.DemoKey;
import com.bob.biz.demoKey.model.DemoKeyVo;
import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.service.DemoLockService;
import com.bob.core.base.BaseServiceTest;
import com.github.pagehelper.PageInfo;

public class DemoKeyServiceTest extends BaseServiceTest {

    @Autowired
    private DemoKeyService demoKeyService;
    @Autowired
    private DemoLockService demoLockService;

    public static String id;

    @BeforeClass
    public static void startTest() {
        System.out.println("单元测试开始");
    }

    @Before
    public void before() {
        DemoLock demoLock = new DemoLock();
        demoLock.setBrand("三环");
        String lockId = demoLockService.add(demoLock);
        DemoKey demoKey = new DemoKey();
        demoKey.setLockId(lockId);
        demoKey.setBrand("三环");
        id = demoKeyService.add(demoKey);
        demoKeyService.add(demoKey);
    }

    @Test
    public void testGetById() {
        DemoKey demoKey = demoKeyService.getById(id);
        Assert.assertNotNull(demoKey);
    }

    @Test
    public void testGetVoById() {
        DemoKeyVo demoKeyVo = demoKeyService.getVoById(id);
        Assert.assertNotNull(demoKeyVo);
    }

    @Test
    public void testGetList() {
        List<DemoKey> list = demoKeyService.getList(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoKey demoKey : list) {
            System.out.println(demoKey);
        }
    }

    @Test
    public void testGetVoList() {
        List<DemoKeyVo> list = demoKeyService.getVoList(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoKeyVo demoKeyVo : list) {
            System.out.println(demoKeyVo);
        }
    }

    @Test
    public void testGetPage() {
        PageInfo<DemoKey> page = demoKeyService.getPage(0, 10, null);
        List<DemoKey> list = page.getList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoKey demoKey : list) {
            System.out.println(demoKey);
        }
    }

    @Test
    public void testGetVoPage() {
        PageInfo<DemoKeyVo> page = demoKeyService.getVoPage(0, 10, null);
        List<DemoKeyVo> list = page.getList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoKeyVo demoKeyVo : list) {
            System.out.println(demoKeyVo);
        }
    }

    @Test
    public void testUpdateById() {
        DemoKey demoKey = demoKeyService.getById(id);
        String updateId = demoKeyService.updateById(demoKey);
        Assert.assertNotNull("updateById success", updateId);
    }

    @Test
    public void testDelById() {
        DemoKey key1 = demoKeyService.getById(id);
        Assert.assertNotNull(key1);
        boolean delById = demoKeyService.delById(id);
        Assert.assertTrue("delById success", delById);
        DemoKey key2 = demoKeyService.getById(id);
        Assert.assertNull(key2);
    }

    @AfterClass
    public static void endTest() {
        System.out.println("单元测试结束");
    }
}
