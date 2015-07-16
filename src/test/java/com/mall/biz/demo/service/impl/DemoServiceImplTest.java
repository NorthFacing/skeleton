package com.mall.biz.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.biz.demo.model.DemoModel;
import com.mall.biz.demo.model.DemoModelVo;
import com.mall.biz.demo.service.DemoService;
import com.mall.core.base.BaseServiceTest;

public class DemoServiceImplTest extends BaseServiceTest {

    private static String name;
    private static Integer age;
    private static LocalDateTime birthDay;
    private static Double score;
    private static Boolean isGraduated;

    @Autowired
    private static DemoModel model;
    @Autowired
    private DemoModelVo vo;

    @Autowired
    private DemoService demoService;

    @BeforeClass
    public static void checkInit() {
        model = new DemoModel();
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
        assert (id != null);
        DemoModel result = (DemoModel) demoService.getById(id);
        assert (result != null && result.getName() != null);
    }

    @Test
    public void testGetById() {
        String id = demoService.add(model);
        assert (id != null);
        DemoModel result = (DemoModel) demoService.getById(id);
        assert (result != null && result.getName() != null);
    }

    @Test
    public void testGetList() {

        String id = demoService.add(model);
        assert (id != null);

        DemoModel modelTwo = new DemoModel();
        modelTwo.setName(name + name);
        String idTwo = demoService.add(modelTwo);
        assert (idTwo != null);

        vo.setName(name);
        List<Object> list = demoService.getList(vo);

        assert (list.size() >= 1);

    }

    @Test
    public void testUpdate() {
        String id = demoService.add(model);
        assert (id != null);

        String newName = new StringBuffer(model.getName()).reverse().toString();
        demoService.updateById(model);

        DemoModel result = (DemoModel) demoService.getById(id);
        assert (result != null && result.getName() != null && newName.equals(result.getName()));
    }

    @Test
    public void testDelById() {
        String id = demoService.add(model);
        assert (id != null);

        boolean b = demoService.delById(id);
        assert (b);

        DemoModel result = (DemoModel) demoService.getById(id);
        assert (null == result);
    }

}
