package com.bob.core.base;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = { "/applicationContext.xml", "/spring-mybatis.xml", "/aop.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseServiceTest extends TestCase {

    @BeforeClass
    public static void start() {
        System.out.println("=====================  start  =======================");
    }

    @Test
    public void test(){
        System.out.println();
    }

    @AfterClass
    public static void end() {
        System.out.println("=====================  end  =======================");
    }
}
