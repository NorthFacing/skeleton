package com.bob.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("path", sc.getContextPath());

        // 存储上下文供velocity工具包调用
//        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
//        ApplicationContextUtil.setContext(applicationContext);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
