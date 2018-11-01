package com.aotain.common.util;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aotain.common.config.dao.PushMapper;
import com.aotain.common.config.model.PushMessage;
import com.aotain.common.utils.model.push.SendData;
import com.aotain.common.utils.push.PushClient;
import com.aotain.common.utils.tools.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })
public class PushTest1 {

	@Autowired
	PushClient PushClient;
	
	@Autowired
	private PushMapper pushMapper;
	
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-base.xml");
    }
@Test
public void mailTest() {
	Properties pro = FileUtils.loadPropertiesFromConfig("config","push.properties");
	SendData message = new SendData();
	PushMessage record = new PushMessage();
}
}
