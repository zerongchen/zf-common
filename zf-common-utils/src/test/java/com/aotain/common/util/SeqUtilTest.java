package com.aotain.common.util;

import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.redis.MessageNoUtil;
import com.aotain.common.utils.redis.MessageSequenceNoUtil;
import com.aotain.common.utils.redis.TaskIdUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/01/30
 */
public class SeqUtilTest extends BaseTest{

    @Autowired
    private BaseRedisService<String,String,String> baseRedisService;

    @Test
    public void testRedis(){
        baseRedisService.putHash("bang","test","123456");
    }

    @Test
    public void testTaskId(){
        System.out.println(TaskIdUtil.getInstance().getTaskId()+"===");
    }

    @Test
    public void testMessageNo(){
        System.out.println(MessageNoUtil.getInstance().getMessageNo(0)+"===");

    }

    @Test
    public void testMessageSeqNo(){
        System.out.println(MessageSequenceNoUtil.getInstance().getSequenceNo(0)+"===");

    }
}
