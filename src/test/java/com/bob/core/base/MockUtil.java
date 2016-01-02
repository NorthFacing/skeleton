package com.bob.core.base;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MockUtil {

    public static String mock(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
        return mvc
                .perform(
                        post(uri, "json").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
                                .content(json.getBytes())).andReturn().getResponse().getContentAsString();
    }

}
