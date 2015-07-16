package com.mall.core.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @created 2015年7月3日 下午11:54:10
     */
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e instanceof Exception) {
            LOG.error(e.getMessage());
        }
    }
    
}
