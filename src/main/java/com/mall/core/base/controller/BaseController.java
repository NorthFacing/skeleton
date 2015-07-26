package com.mall.core.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mall.core.utils.AjaxResults;
import com.mall.core.utils.ServletUtils;

/**
 * BaseController公共类
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月3日 下午6:11:56
 */
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * 异常处理
     * 
     * @since v0.0.1
     * @author Bob
     * @throws IOException 
     * @created 2015年7月3日 下午11:54:10
     */
    public AjaxResults<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        if (e instanceof IllegalArgumentException) {
            LOG.error(e.getMessage());
            if (ServletUtils.isAjax(request)) {
                return new AjaxResults<Object>(404, "参数不合法！");
            }else{
                response.sendRedirect("/");
            }
        }
        return new AjaxResults<Object>("程序出错！");
    }

}
