package com.bob.core.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bob.core.contants.Constants;
import com.bob.core.utils.ApplicationContextUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.URL;

public class ApplicationListener implements ServletContextListener {

  private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("path", sc.getContextPath());

    // 存储上下文供velocity工具包调用
    WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
    ApplicationContextUtil.setContext(applicationContext);

    // 日志路径配置
    initLogModule();

  }

  private void initLogModule() {
    try {
      URL url = ResourceUtils.getURL("classpath:logModule.json");
      String data = FileUtils.readFileToString(new File(url.getFile()), "utf-8");

      JSONArray jsonArray = JSON.parseArray(data);

      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Constants.logMap.put(jsonObject.getString("url"), jsonObject);
      }
    } catch (Exception e) {
      logger.error("加载classpath:logModule.json失败。", e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }

}
