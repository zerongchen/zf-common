package com.aotain.common.utils.aop;

import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;
import com.aotain.common.utils.monitorstatistics.ExceptionCollector;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 线程状态切面类，当其他地方调用addEvent方法时，增加方法调用时间
 *
 * @author daiyh@aotain.com
 * @date 2017/12/25
 */
@Component
@Aspect
public class ThreadMonitorHandler {

    private static Logger logger = LoggerFactory.getLogger(ThreadMonitorHandler.class);

    @Autowired
    private BaseRedisService<String,String,String> baseRedisService;

    @Pointcut("execution(* com.aotain.common.utils.monitorstatistics.ExceptionCollector.addEvent(String,String,int))")
    public void proxyAspect() {

    }

    @Around("proxyAspect()")
    public Object doInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try{
            Object[] args = proceedingJoinPoint.getArgs();
            String module = (String)args[0];
            String type = (String)args[1];
            String field = String.format(RedisKeyConstant.THREAD_MONITOR_HASHKEY,module,type);
            logger.info("record the last executor time had successed...,the module is "+module+" and the type is "+type);
            baseRedisService.putHash(RedisKeyConstant.THREAD_MONITOR_REDIS_KEY,
                    ExceptionCollector.getThreadMonitorKeyByField(field),System.currentTimeMillis()/1000+"");
        } catch (Exception e){
            logger.error("add executor time before calling the addEvent method  of the thread ",e);
        }
        return proceedingJoinPoint.proceed();
    }
}
