package com.aotain.common.utils.monitorstatistics;

import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;
import com.aotain.common.utils.file.MonitorContentToFileUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/12/28
 */
@Component
public class ExceptionMonitorTask {

    private static Logger logger = LoggerFactory.getLogger(ExceptionMonitorTask.class);

    private static String threadException = "1";

    public static int timeInterval = 3 * 60;

    @Autowired
    private ExceptionCollector exceptionCollector;

    @Autowired
    BaseRedisService<String,String,String> baseRedisService;

    /**
     * 将redis中保存的异常信息写入文件
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void generatorExceptionMonitorFile () {

        try{
            Map<String,String> monitorMap = exceptionCollector.getMonitorMap();
            Map<String,Map<String,String>> allModuleMap = getAllModuleMonitorMap(monitorMap);
            if (allModuleMap==null){
                logger.info("there is no monitor data so it is can not generating the file");
                return;
            }
            for(Map.Entry<String,Map<String,String>> entry:allModuleMap.entrySet()){
                Map<String,String> oneModuleMap = entry.getValue();
                MonitorContentToFileUtil.writeMessageToFile(entry.getKey(),oneModuleMap);
            }
            revertRedisMap(monitorMap);
            logger.info("write redis exception content to file had been successed...");

//            writeServerStatusFile();
        } catch (Exception e) {
            logger.error("generatorExceptionMonitorFile failed...",e);
            exceptionCollector.addEvent(e);
        }

    }


    /**
     * 根据maps中的数据分模块放到对应的map中
     * @param maps
     * @return
     */
    private Map<String,Map<String,String>> getAllModuleMonitorMap(Map<String,String> maps){
        if ( maps == null ) {
            return Maps.newHashMap();
        }

        Map<String,Map<String,String>> resultMap = Maps.newHashMap();

        for ( Map.Entry<String,String> entry :maps.entrySet() ){
            String key = entry.getKey();
            String value = entry.getValue();
            String module = key.split("_")[0];
            String type = key.split("_")[1];

            // 如果是线程监控数据  超过一定时间间隔未更新上报时间 则认为此线程异常 修改值为1
            if (TypeConstant.EXCEPTION_TYPE_THREAD.equals(type)) {
                if ( updateThreadStatus(module,type) ){
                    value = threadException;
                }
            }

            if ( resultMap.containsKey(module)) {
                Map<String,String> moduleMap = resultMap.get(module);
                moduleMap.put(key,value);
                resultMap.put(module,moduleMap);

            } else {
                Map<String,String> newModuleMap = Maps.newHashMap();
                newModuleMap.put(key,value);
                resultMap.put(module,newModuleMap);

            }

        }

        return resultMap;
    }

    /**
     * 生成文件后将redis中key对应value重置为0
     * @param maps
     */
    private void revertRedisMap(Map<String,String> maps){
        if (maps==null){
            return;
        }
        for(Map.Entry<String,String> entry:maps.entrySet()){
            maps.put(entry.getKey(),"0");
        }
    }


    /**
     * 补充模块中不存在的key value值
     * @param module
     * @param moduleMap
     * @return
     */
    private Map<String,String> fillModuleMap(String module,Map<String,String> moduleMap){
        String format = "%s_%s_exception";
        String key1 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_DB);
        String key2 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_REDIS);
        String key3 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_KAFKA);
        String key4 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_HIVE);
        String key5 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_THREAD);
        String key6 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_APPLICATION);
        String key7 = String.format(format,module,TypeConstant.EXCEPTION_TYPE_OTHER);
        if(!moduleMap.containsKey(key1)){
            moduleMap.put(key1,"0");
        }
        if(!moduleMap.containsKey(key2)){
            moduleMap.put(key2,"0");
        }
        if(!moduleMap.containsKey(key3)){
            moduleMap.put(key3,"0");
        }
        if(!moduleMap.containsKey(key4)){
            moduleMap.put(key4,"0");
        }
        if(!moduleMap.containsKey(key5)){
            moduleMap.put(key5,"0");
        }
        if(!moduleMap.containsKey(key6)){
            moduleMap.put(key6,"0");
        }
        if(!moduleMap.containsKey(key7)){
            moduleMap.put(key7,"0");
        }
        return moduleMap;
    }

    /**
     * 线程监控上次调用时间是否超过时间间隔
     * @param module
     * @param type
     * @return
     */
    private boolean updateThreadStatus(String module,String type){
        boolean result = true;
        try{
            String field = String.format(RedisKeyConstant.THREAD_MONITOR_HASHKEY,module,type);
            String value = baseRedisService.getHash(RedisKeyConstant.THREAD_MONITOR_REDIS_KEY,ExceptionCollector.getThreadMonitorKeyByField(field));
            if ( !StringUtils.isEmpty(value) ) {
                long lastExecutorTime = Long.valueOf(value);
                result = System.currentTimeMillis()/1000 - lastExecutorTime > timeInterval;
            }

        } catch (Exception e){
            logger.error("updateThreadStatus failed...Param[module="+module+",type="+type+"]",e);
            return false;
        }

        return result;
    }

}
