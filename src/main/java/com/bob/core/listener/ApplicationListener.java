package com.bob.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

  private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("path", sc.getContextPath());

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }

}
