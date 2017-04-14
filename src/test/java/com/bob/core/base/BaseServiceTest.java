package com.bob.core.base;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-mybatis.xml", "/aop.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@Transactional
// @Rollback 回滚，取消测试数据
// @Commit   提交，保持测试数据
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 指定方法顺序为：按照名称排序
public class BaseServiceTest extends TestCase {

  private static final Logger logger = LoggerFactory.getLogger(BaseServiceTest.class);

  @BeforeClass
  public static void start() {
    logger.debug("=====================  start  =======================");
  }

  @AfterClass
  public static void end() {
    logger.debug("=====================  end  =======================");
  }

}
