package com.aotain.common.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/01/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })
public class BaseTest {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-base.xml");
    }

    @Test
    public void test(){

    }
}
