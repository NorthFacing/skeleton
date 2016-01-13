package com.bob.core.base;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Bob on 2016/1/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-mybatis.xml", "/aop.xml", "/spring-shiro.xml", "/spring-servlet.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseMockTest extends TestCase {

    @Autowired
    WebApplicationContext wac;

    public MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    public JSONObject restJsonPost(String uri, Object obj) throws Exception {
        String result = mockMvc.perform
                (
                        post(uri, "json")
                                .characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSONObject.toJSONString(obj))
                )
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return JSONObject.parseObject(result);
    }

    public JSONObject restJsonGet(String uri, Object obj) throws Exception {
        String result = mockMvc.perform
                (
                        get(uri, "json")
                                .characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSONObject.toJSONString(obj).getBytes())
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        return JSONObject.parseObject(result);
    }

}
