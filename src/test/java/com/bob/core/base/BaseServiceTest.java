package com.bob.core.base;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.base.service.BaseService;

import java.time.LocalDateTime;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-mybatis.xml", "/aop.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseServiceTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceTest.class);

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private BaseService baseService;

    @BeforeClass
    public static void start() {
        logger.debug("=====================  start  =======================");
    }

    @AfterClass
    public static void end() {
        logger.debug("=====================  end  =======================");
    }

    public BaseEntity getBaseEntity() {
        BaseEntity baseEntity = new BaseEntity();
        return baseEntity;
    }

    @Test
    public void insertTest() {
        BaseEntity baseEntity = getBaseEntity();
        baseService.insert(baseEntity);
        Assert.assertNotNull(baseEntity.getId());
    }

    @Test
    public void updateTest() {
        BaseEntity baseEntity = getBaseEntity();
        baseService.insert(baseEntity);
        baseEntity.setCreateTime(LocalDateTime.now());
        baseService.update(baseEntity);
    }

    @Test
    public void deleteTest() {
        BaseEntity param1 = getBaseEntity();
        baseService.insert(param1);

        BaseEntity param2 = new BaseEntity();
        param2.setId(param1.getId());
        baseService.delete(param2);
    }

}
