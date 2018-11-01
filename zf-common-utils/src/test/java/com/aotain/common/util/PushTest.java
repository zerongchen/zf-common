package com.aotain.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.aotain.common.utils.model.push.PushMessageRecord;
import com.aotain.common.utils.model.push.PushReceiver;
import com.aotain.common.utils.model.push.SendData;
import com.aotain.common.utils.push.PushClient;
import com.aotain.common.utils.tools.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })
public class PushTest {

	@Autowired
	PushClient PushClient;
	
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-base.xml");
    }
@Test
public void mailTest() {
//	Properties pro = FileUtils.loadPropertiesFromConfig("config","push.properties");
//	SendData message = new SendData();
	
	List<PushMessageRecord> record = new ArrayList<PushMessageRecord>();
	PushMessageRecord pm =  new PushMessageRecord();
	pm.setPushId((long)4);
	PushReceiver receiver = new PushReceiver();
	List<String> dpId = new ArrayList<String>();
	dpId.add("tanzj@aotain.com");
	receiver.setEmailReceiver(dpId);
	pm.setReceiver(receiver);
	pm.setPushType(1);
	record.add(pm);
//	PushClient.pushMessage(record);
}
}
