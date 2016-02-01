package com.bob.modules.login;

import com.bob.core.base.BaseMockTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Bob on 2016/1/12.
 */
public class LoginControllerTest extends BaseMockTest {

  @Test
  public void loginTest() throws Exception {
    mockMvc.perform(get("/admin/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("/login/login"));
  }

  @Test
  public void loginActionTest() throws Exception {
    mockMvc.perform
        (
            post("/admin/loginAction")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\"userName\":\"admin\",\"password\":\"123456\"}")
                .param("userName", "admin")
                .param("password", "123456")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(request().attribute("error", ""))
        .andExpect(view().name("/login/loginSuc"));
  }
}
