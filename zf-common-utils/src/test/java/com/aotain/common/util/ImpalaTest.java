package com.aotain.common.util;

import com.aotain.common.utils.hadoop.HadoopUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Demo class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })
public class ImpalaTest {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-base.xml");
    }

    @Test
    public void test(){
        Connection conn = HadoopUtils.getHiveConnection();
        ResultSet resultSet = HadoopUtils.executeQuery(conn, " select * from zf.job_ubas_traffic_d where dt=20180620");
        try {
            while (resultSet.next()){
                    System.out.println(resultSet.getInt(1));
                    System.out.println(resultSet.getString(2));
                    System.out.println(resultSet.getString(3));
                    System.out.println(resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            if (conn!=null){
                HadoopUtils.close(conn);
            }
        }
    }
}
