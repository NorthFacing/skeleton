package com.bob.biz.demoLock.service;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.biz.demoKey.model.DemoKey;
import com.bob.biz.demoKey.service.DemoKeyService;
import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.model.DemoLockVo;
import com.bob.core.base.BaseServiceTest;
import com.github.pagehelper.PageInfo;

public class DemoLockServiceTest extends BaseServiceTest {

    @Autowired
    private DemoLockService demoLockService;
    @Autowired
    private DemoKeyService demoKeyService;

    public static String id;

    @BeforeClass
    public static void startTest() {
        System.out.println("单元测试开始");
    }

    @Before
    public void before() {
        DemoLock demoLock = new DemoLock();
        demoLock.setBrand("三环");
        id = demoLockService.add(demoLock);
        DemoKey demoKey = new DemoKey();
        demoKey.setLockId(id);
        demoKey.setBrand("三环");
        demoKeyService.add(demoKey);
        demoKeyService.add(demoKey);
    }

    @Test
    public void testGetById() {
        DemoLock demoLock = demoLockService.getById(id);
        Assert.assertNotNull(demoLock);
    }

    @Test
    public void testGetVoById() {
        DemoLockVo demoLockVo = demoLockService.getVoById(id);
        Assert.assertNotNull(demoLockVo);
        List<DemoKey> demoKeyList = demoLockVo.getDemoKeyList();
        Assert.assertNotNull(demoKeyList);
        for (DemoKey demoKey : demoKeyList) {
            Assert.assertTrue(demoLockVo.getId().equals(demoKey.getLockId()));
        }
    }

    @Test
    public void testGetList() {
        List<DemoLock> list = demoLockService.getList(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetVoList() {
        List<DemoLockVo> list = demoLockService.getVoList(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoLockVo demoLockVo : list) {
            List<DemoKey> demoKeyList = demoLockVo.getDemoKeyList();
            Assert.assertNotNull(demoKeyList);
            for (DemoKey demoKey : demoKeyList) {
                Assert.assertTrue(demoLockVo.getId().equals(demoKey.getLockId()));
            }
        }
    }

    @Test
    public void testGetPage() {
        PageInfo<DemoLock> page = demoLockService.getPage(0, 10, null);
        List<DemoLock> list = page.getList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetVoPage() {
        PageInfo<DemoLockVo> page = demoLockService.getVoPage(0, 10, null);
        List<DemoLockVo> list = page.getList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        for (DemoLockVo demoLockVo : list) {
            List<DemoKey> demoKeyList = demoLockVo.getDemoKeyList();
            Assert.assertNotNull(demoKeyList);
            for (DemoKey demoKey : demoKeyList) {
                Assert.assertTrue(demoLockVo.getId().equals(demoKey.getLockId()));
            }
        }
    }

    @Test
    public void testUpdateById() {
        DemoLock demoLock = demoLockService.getById(id);
        String updateId = demoLockService.updateById(demoLock);
        Assert.assertNotNull(updateId);
    }

    @Test
    public void testDelById() {
        boolean delById = demoLockService.delById(id);
        Assert.assertTrue(delById);
    }

    @AfterClass
    public static void endTest() {
        System.out.println("单元测试结束");
    }
}
