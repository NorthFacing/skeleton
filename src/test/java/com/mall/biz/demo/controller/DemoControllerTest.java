package com.mall.biz.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mall.biz.demo.service.DemoService;
import com.mall.core.base.BaseServiceTest;

public class DemoControllerTest extends BaseServiceTest{

    @Mock
    private DemoService demoService;
    @InjectMocks
    private DemoController demoController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }

    @Test
    public void testAdd() throws Exception {
        mockMvc.perform(post("/demo/add").param("name", "Bob Mock").param("age", "28").param("birthDay", "1988-04-01"));
    }

    // Rest接口测试范例
    // mockMvc
    // .perform(
    // post("/demo/add", "json").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
    // .content(json.getBytes())).andReturn().getResponse().getContentAsString();

    @Test
    public void testGetById() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetList() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPage() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelById() {
        fail("Not yet implemented");
    }

}
