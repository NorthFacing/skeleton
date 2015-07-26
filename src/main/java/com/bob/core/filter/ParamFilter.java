package com.bob.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 参数过滤：将参数进行trim()处理
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月3日 下午9:25:33
 */
public class ParamFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
    }
}