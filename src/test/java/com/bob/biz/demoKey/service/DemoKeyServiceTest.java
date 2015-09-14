package com.bob.biz.demoKey.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.biz.demoKey.model.DemoKey;
import com.bob.biz.demoKey.model.DemoKeyVo;
import com.bob.core.base.BaseServiceTest;
import com.github.pagehelper.PageInfo;

public class DemoKeyServiceTest extends BaseServiceTest {

    @Autowired
    private DemoKeyService demoKeyService;

    public static String id;

    @Before
    public void startTest() {
        System.out.println("单元测试开始");
        {
            DemoKey demoKey = new DemoKey();
            demoKey.setBrand("三环");
            id = demoKeyService.add(demoKey);
            Assert.assertNotNull("添加成功【demoKey ：" + id + "】", id);
            demoKeyService.add(demoKey);
        }
    }

    @Test
    public void testGetById() {
        DemoKey demoKey = demoKeyService.getById(id);
        Assert.assertNotNull("getById success", demoKey);
    }

    @Test
    public void testGetVoById() {
        DemoKeyVo demoKeyVo = demoKeyService.getVoById(id);
        Assert.assertNotNull("getVoById success", demoKeyVo);
    }

    @Test
    public void testGetList() {
        List<DemoKey> list = demoKeyService.getList(null);
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoKey demoKey : list) {
            System.out.println(demoKey);
        }
    }

    @Test
    public void testGetVoList() {
        List<DemoKeyVo> list = demoKeyService.getVoList(null);
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoKeyVo demoKeyVo : list) {
            System.out.println(demoKeyVo);
        }
    }

    @Test
    public void testGetPage() {
        PageInfo<DemoKey> page = demoKeyService.getPage(0, 10, null);
        List<DemoKey> list = page.getList();
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
        for (DemoKey demoKey : list) {
            System.out.println(demoKey);
        }
    }

    @Test
    public void testGetVoPage() {
        PageInfo<DemoKeyVo> page = demoKeyService.getVoPage(0, 10, null);
        List<DemoKeyVo> list = page.getList();
        Assert.assertNotNull("getList success", list);
        Assert.assertTrue("getList size > 0", list.size() > 0);
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
        boolean delById = demoKeyService.delById(id);
        Assert.assertTrue("delById success", delById);
    }

    @After
    public void endTest() {
        System.out.println("单元测试结束");
    }
}
