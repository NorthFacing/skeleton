package com.bob.modules.login;

import com.bob.core.base.BaseMockTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Bob on 2016/1/12.
 */
public class LoginControllerTest extends BaseMockTest {

    @Test
    public void test() throws Exception {
        mockMvc.perform(post("/admin/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login/login"))
                .andExpect(jsonPath("$.firstName").value("Barney"));
    }
}
