package com.mall.biz.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//import com.github.pagehelper.PageInfo;
import com.mall.biz.demo.model.Demo;
import com.mall.biz.demo.service.DemoService;
import com.mall.core.base.BaseServiceTest;

public class DemoServiceImplTest extends BaseServiceTest {

    private static String name;
    private static Integer age;
    private static LocalDateTime birthDay;
    private static Double score;
    private static Boolean isGraduated;

    private static Demo model;

    @Autowired
    private DemoService demoService;

    @BeforeClass
    public static void checkInit() {
        model = new Demo();
        // 初始化随机参数
        Random r = new Random();
        Integer i = r.nextInt(60);
        name = "Name" + i;
        age = i;
        birthDay = LocalDateTime.now();
        score = Double.parseDouble(i.toString());
        isGraduated = (i > 30);
        // 给model赋值
        model.setName(name).setAge(age).setBirthDay(birthDay).setScore(score).setIsGraduated(isGraduated);
    }

    @Test
    public void testAdd() {
        String id = demoService.add(model);
        Assert.assertNotNull("add 方法返回值为空！", id);
    }

    @Test
    public void testGetById() {
        String id = demoService.add(model);
        Assert.assertNotNull("add 方法返回值为空！", id);

        Demo result = (Demo) demoService.getById(id);
        Assert.assertNotNull("getById 方法返回值为空！", result);
        Assert.assertNotNull("getById 方法返回值 name 为空！", result.getName());
    }

    @Test
    public void testGetList() {

        int j = 35;

        for (int i = 0; i < j; i++) {
            model.setName(name + i);
            String id = demoService.add(model);
            Assert.assertNotNull("add 方法返回值为空！", id);
        }

        String newName = new StringBuffer(name).reverse().toString();
        for (int i = 0; i < j; i++) {
            model.setName(newName + i);
            String id = demoService.add(model);
            Assert.assertNotNull("add 方法返回值为空！", id);
        }

        List<Demo> list01 = demoService.getList(null);
        Assert.assertTrue("getList 方法 查询结果不正确！", list01.size() >= j * 2);

        // List<Demo> list02 = demoService.getList(model);
        // Assert.assertTrue("getList 方法 查询结果不正确！", list02.size() * 2 <= list01.size());

    }

    @Test
    public void TestgetPage() {

        for (int i = 0; i < 35; i++) {
            model.setName(name + i);
            demoService.add(model);
        }

        List<Demo> list = demoService.getList(null);
        Assert.assertTrue("getList 方法 查询结果不正确！", list.size() >= 35);

        // // int pageNum = 2;
        // int pageSize = 6;

        // PageInfo<Demo> page = demoService.getPage(null);
        // Assert.assertTrue("", page.getList().size() == pageSize);
    }

    @Test
    public void testUpdate() {
        String id = demoService.add(model);
        Assert.assertNotNull("update 方法返回值为空！", id);

        String newName = new StringBuffer(name).reverse().toString();
        model.setName(newName);
        demoService.updateById(model);

        Demo result = (Demo) demoService.getById(id);
        Assert.assertNotNull("getById 方法返回值为空！", result);
        Assert.assertNotNull("getById 方法返回值 name 为空！", result.getName());
        Assert.assertEquals(name, new StringBuffer(result.getName()).reverse().toString());
    }

    @Test
    public void testDelById() {
        String id = demoService.add(model);
        Assert.assertNotNull("update 方法返回值为空！", id);

        boolean b = demoService.delById(id);
        Assert.assertTrue(b);

        Demo result = (Demo) demoService.getById(id);
        Assert.assertNull(result);
    }

}
