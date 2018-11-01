package com.aotain.common.config;

import com.alibaba.fastjson.JSON;
import com.aotain.common.config.model.ClusterHouses;
import com.aotain.common.config.model.IdcHouses;
import com.aotain.common.config.redis.BaseRedisDao;
import com.aotain.common.config.redis.BaseRedisService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author daiyh@aotain.com
 * @date 2017/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })
public class BaseTest {

	public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	private ApplicationContext applicationContext;
	
	@Autowired
    private BaseRedisDao baseRedisDao;

    @Autowired
    private BaseRedisService<String, String, String> baseRedisServiceImpl;

    
    private static final String IDC_JDCM_JKCS_CONFIG = "idc_jdcm_jkcs_config";
	
	private static final String KEY_IDC_HOUSES = "IdcHouses";
	
	private static final String KEY_CLUSTER_HOUSE = "ClusterHouseConfiguration";

	@PostConstruct
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-base.xml");
	}
	
	@Test
	@Rollback(false)
	public void test() {
		applicationContext.getApplicationName();
//		BasicDataSource basicDataSource = applicationContext.getBean("dataSource", BasicDataSource.class);
		RedisTemplate redisTemplate = applicationContext.getBean("redisTemplate", RedisTemplate.class);
		SqlSessionFactory sqlSessionFactory = applicationContext.getBean("sqlSessionFactory",SqlSessionFactory.class);
		DataSourceTransactionManager dataSourceTransactionManager = applicationContext.getBean("transactionManager",DataSourceTransactionManager.class);
		System.out.println("dataSourceTransactionManager="+dataSourceTransactionManager);
		System.out.println(redisTemplate + "==========");
//		System.out.println(basicDataSource + "==========");
		System.out.println(sqlSessionFactory+"========");
//		redisTemplate.opsForHash().put("somekey1", "bang", "daiyuhang");
		System.out.println("=====ok");
	}
	
	@Test
	@Rollback(false)
	public void testInitConfig() {
		
		/*String idcId = "A2.B1.B2-20090001dsads";
		Map<String, IdcHouses> allHouse = LocalConfig.getInstance().getAllIdcHouses();
		Iterator<String> it = allHouse.keySet().iterator();
		while (it.hasNext()) {
			String houseIdStr = it.next();
			IdcHouses idcHouse = allHouse.get(houseIdStr);
			idcId = idcHouse.getIdcId();
			break;
		}
		System.out.println("idcId:" + idcId);*/
		
		System.out.println(LocalConfig.getInstance().getAzkabanUrlByHouseIdStr("GD_ATKJ_IDC"));
		System.out.println(LocalConfig.getInstance().getNameNodeInfoByHouseIdStr("GD_ATKJ_IDC"));
	}
	
	@Test
	@Rollback(false)
	public void testGet() {
		logger.info("testGet start....");
		IdcHouses house = LocalConfig.getInstance().getIdcHouse("GD_ATKJ_IDC");
		System.out.println("house.getHouseIdStr():" + house.getHouseIdStr());
		System.out.println(JSON.toJSONString(house));
		
		/*Map<String, IdcHouses> houses = LocalConfig.getInstance().getAllIdcHouses();
		Iterator<String> it = houses.keySet().iterator();
		while (it.hasNext()) {
			String houseIdStr = it.next();
			IdcHouses idcHouses = houses.get(houseIdStr);
			
			System.out.println("houseIdStr:" + houseIdStr + ",idcHouses:" + JSON.toJSONString(idcHouses));
		} */
		System.out.println("=====1:" + JSON.toJSONString(LocalConfig.getInstance().getAllIdcHouses(null)));
		System.out.println("=====2:" + JSON.toJSONString(LocalConfig.getInstance().getAllIdcHouses(false)));
		System.out.println("=====3:" + JSON.toJSONString(LocalConfig.getInstance().getAllIdcHouses(true)));
	}

	@Test
	public void testSystem(){
		String value = System.getProperty("user.dir");
		System.out.println("value="+value);
	}

	@Test
	public void testZSet() {
		boolean result = baseRedisServiceImpl.addZSet("StrategySorted_1_15","messageNo_1",50L);
		System.out.println("====ok====="+result);
		long removeZSet = baseRedisServiceImpl.removeZSet("StrategySorted_1_15","messageNo_1");
		System.out.println(removeZSet+"======");
	}
	
}
