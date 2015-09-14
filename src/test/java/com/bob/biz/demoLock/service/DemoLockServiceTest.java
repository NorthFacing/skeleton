package com.bob.biz.demoLock.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.model.DemoLockVo;
import com.bob.core.base.BaseServiceTest;
import com.github.pagehelper.PageInfo;

public class DemoLockServiceTest extends BaseServiceTest {

    @Autowired
    private DemoLockService demoLockService;

    public static String id;

    @Before
    public void startTest() {
        System.out.println("单元测试开始");
        {
            DemoLock demoLock = new DemoLock();
            demoLock.setBrand("三环");
            id = demoLockService.add(demoLock);
            Assert.assertNotNull("添加成功【demoLock ：" + id + "】", id);
        }
    }

    @Test
    public void testGetById() {
        DemoLock demoLock = demoLockService.getById(id);
        Assert.assertNotNull("getById success", demoLock);
    }

    @Test
    public void testGetVoById() {
        DemoLockVo demoLockVo = demoLockService.getVoById(id);
        Assert.assertNotNull("getVoById success", demoLockVo);
    }

    @Test
    public void testGetList() {
        List<DemoLock> list = demoLockService.getList(null);
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoLock demoLock : list) {
            System.out.println(demoLock);
        }
    }

    @Test
    public void testGetVoList() {
        List<DemoLockVo> list = demoLockService.getVoList(null);
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoLockVo demoLockVo : list) {
            System.out.println(demoLockVo);
        }
    }

    @Test
    public void testGetPage() {
        PageInfo<DemoLock> page = demoLockService.getPage(0, 10, null);
        List<DemoLock> list = page.getList();
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoLock demoLock : list) {
            System.out.println(demoLock);
        }
    }

    @Test
    public void testGetVoPage() {
        PageInfo<DemoLockVo> page = demoLockService.getVoPage(0, 10, null);
        List<DemoLockVo> list = page.getList();
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoLockVo demoLockVo : list) {
            System.out.println(demoLockVo);
        }
    }

    @Test
    public void testUpdateById() {
        DemoLock demoLock = demoLockService.getById(id);
        String updateId = demoLockService.updateById(demoLock);
        Assert.assertNotNull("updateById success", updateId);
    }

    @Test
    public void testDelById() {
        boolean delById = demoLockService.delById(id);
        Assert.assertTrue("delById success", delById);
    }

    @After
    public void endTest() {
        System.out.println("单元测试结束");
    }
}
