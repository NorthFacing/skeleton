package com.bob.biz.demoKey.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bob.core.base.BaseServiceTest;

public class DemoKeyControllerTest extends BaseServiceTest {

    @InjectMocks
    private DemoKeyController demoKeyController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(demoKeyController).build();
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/demoKey/save").param("brand", "三环").param("", ""));
    }

    @Test
    public void testGetById() {
        fail("Not yet implemented");
    }

    @Test
    public void testListHtml() {
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

}
