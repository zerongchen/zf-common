package com.aotain.common.policyapi.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aotain.common.config.LocalConfig;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.policyapi.constant.*;
import com.aotain.common.policyapi.model.*;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.aotain.common.utils.constant.RedisKeyConstant;
import com.aotain.common.utils.model.msg.RedisTaskStatus;

import com.aotain.common.utils.model.msg.StrategySendChannel;
import com.aotain.common.utils.redis.PolicyAckUtil;

import com.aotain.common.utils.redis.TaskIdUtil;
import com.aotain.common.utils.redis.TaskMessageUtil;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 *
 * @description:
 *         业务层基类，这里主要核心方法是：addPolicy、modifyPolicy、deletePolicy、ManualRetransPolicy（手动重发策略）
 *         另外，关于addDb、modifyDb、deleteDb都没有实现；
 *         自定义的逻辑方法 ：addCustomLogic、modifyCustomLogic、deleteCustomLogic
 * @author daiyh
 * @date 2018年1月15日 下午5:04:04
 * 注意：此策略都是基于DPI设备（省汇聚下有DPI设备和EU设备，都作为DPI设备类型发策略）
 */
@Service
public abstract class BaseService {

    /**
     * 写日志
     */
    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected BaseRedisService<String, String, String> rediscluster;


    /**
     * 待实现，添加数据库，需要的策略重写该方法
     * @param policy
     * @return
     */
    protected abstract boolean addDb(BaseVO policy);
    /**
     * 待实现，删除数据库，需要的策略重写该方法
     * @param policy
     * @return
     */
    protected abstract boolean deleteDb(BaseVO policy);

    /**
     * 待实现，修改数据库，需要的策略重写该方法
     * @param policy
     * @return
     */
    protected abstract boolean modifyDb(BaseVO policy);

    /**
     * 各自实现，新增自定义逻辑，主要用于实现与redis相关信息操作
     * 主要用于写StrategySorted_x_x操作顺序信息 写通道信息...
     * @param policy
     * @return
     */
    protected abstract boolean addCustomLogic(BaseVO policy);

    /**
     * 各自实现，修改自定义逻辑，主要用于实现与redis相关信息操作
     * 主要用于写StrategySorted_x_x操作顺序信息 写通道信息...
     * @param policy
     * @return
     */
    protected abstract boolean modifyCustomLogic(BaseVO policy);

    /**
     * 各自实现，删除自定义逻辑，主要用于实现与redis相关信息操作
     * 主要用于写StrategySorted_x_x操作顺序信息 写通道信息...
     * @param policy
     * @return
     */
    protected abstract boolean deleteCustomLogic(BaseVO policy);

    /**
     * 添加单个策略
     * @param policy 单个策略实体类
     * @return
     */
    public final boolean addPolicy(BaseVO policy){
        boolean result = false;
        //添加db操作
        result = addDb(policy);
        if ( !result ){
            logger.error("addDb error,Params="+policy.objectToJson());
        }
        //写策略redis操作
        result = setRedisPolicyHash(policy);
        if ( !result ){
            logger.error("setRedisPolicyHash error,Params="+policy.objectToJson());
        }
        //添加自定义逻辑操作  写操作顺序 写task 写redis通道
        result = addCustomLogic(policy);
        if ( !result ){
            logger.error("addCustomLogic error,Params="+policy.objectToJson());
        }
        return result;
    }

    /**
     * 添加多个策略
     * @param policyList 多个策略
     * @return
     */
    public final boolean addPolicy(List<? extends BaseVO> policyList){

        boolean result = true;
        for(BaseVO policy : policyList){
            result = addPolicy(policy);

            if(!result){
                break;
            }
        }

        return result;

    }

    /**
     * 修改单个策略
     * @param policy
     * @return
     */
    public final boolean modifyPolicy(BaseVO policy){
        boolean result = false;
        //添加db操作
        result = modifyDb(policy);
        if ( !result ){
            logger.error("modifyDb error,Params="+policy.objectToJson());
        }
        //写策略redis操作
        result = setRedisPolicyHash(policy);
        if ( !result ){
            logger.error("setRedisPolicyHash error,Params="+policy.objectToJson());
        }
        //添加自定义逻辑操作  写操作顺序 写task 写redis通道
        result = modifyCustomLogic(policy);
        if ( !result ){
            logger.error("modifyCustomLogic error,Params="+policy.objectToJson());
        }
        return result;
    }

    /**
     * 修改多个策略
     * @param policyList
     * @return
     */
    public final boolean modifyPolicy(List<? extends BaseVO> policyList){
        boolean result = true;
        for(BaseVO policy : policyList){
            result = modifyPolicy(policy);
            
            if(!result){
                break;
            }
        }

        return result;
    }

    /**
     * 删除单个策略
     * @param policy
     * @return
     */
    public final boolean deletePolicy(BaseVO policy){
        boolean result = false;
        //添加db操作
        result = deleteDb(policy);
        if ( !result ){
            logger.error("deleteDb error,Params="+policy.objectToJson());
        }
        //写策略redis操作
        result = setRedisPolicyHash(policy);
        if ( !result ){
            logger.error("setRedisPolicyHash error,Params="+policy.objectToJson());
        }
        //添加自定义逻辑操作  写操作顺序 写task 写redis通道
        result = deleteCustomLogic(policy);
        if ( !result ){
            logger.error("deleteCustomLogic error,Params="+policy.objectToJson());
        }
        return result;
    }

    /**
     * 删除多个策略
     * @param policyList
     * @return
     */
    public final boolean deletePolicy(List<? extends BaseVO> policyList){
        boolean result = true;
        for(BaseVO policy : policyList){
            result = deletePolicy(policy);

            if(!result){
                break;
            }
        }

        return result;
    }


    /**
     * 将policy信息写入redis通用方法
     *    此方法默认key为Strategy_x_x  hashKey为messageNo
     *    特别地，当key 或者 hashKey与此不同时 需要在子类中覆盖此方法
     * @param policy
     * @return
     */
    protected boolean setRedisPolicyHash(BaseVO policy){
        try{

            if ( policy instanceof DpiDeviceInfoStrategy ){
                DpiDeviceInfoStrategy dpiDeviceInfoStrategy = (DpiDeviceInfoStrategy)policy;
                if ( StringUtils.isEmpty(dpiDeviceInfoStrategy.getDpiIp()) ) {
                    logger.error("the ip cannot be null"+policy.objectToJson());
                    return false;
                }
                if ( dpiDeviceInfoStrategy.getDpiPort()== null){
                    logger.error("the port cannot be null"+policy.objectToJson());
                    return false;
                }
                String key = String.format(RedisKeyConstant.REDIS_POLICY_HASH_KEY, ProbeType.DPI.getValue(), policy.getMessageType());
                String hashKey = dpiDeviceInfoStrategy.getDpiIp()+"|"+dpiDeviceInfoStrategy.getDpiPort();
                rediscluster.putHash(key, hashKey, policy.objectToJson());
                logger.info("set policy to redis "+key+" success! msg=" + JSON.toJSONString(policy));
            } else {
                String key = String.format(RedisKeyConstant.REDIS_POLICY_HASH_KEY, ProbeType.DPI.getValue(), policy.getMessageType());
                String hashKey = policy.getMessageNo().toString();
                rediscluster.putHash(key, hashKey, policy.objectToJson());
                logger.info("set policy to redis "+key+" success! msg=" + JSON.toJSONString(policy));
            }

            return true;
        }catch(Exception e){
            logger.error("policy put to redis hash error! msg=" + policy.objectToJson(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
    }

    /**
     * 将policy信息写入redis通用方法
     * 此方法采用zSet数据结构 以messageNo为key messageSequenceNo为score
     * 默认会按messageSequenceNo升序排列
     * @param policy
     * @return
     */
    public boolean setPolicyOperateSequenceToRedis(BaseVO policy){
        try{
            String key = String.format(RedisKeyConstant.REDIS_POLICY_STRATEGY_HASH_KEY, policy.getProbeType(), policy.getMessageType());
            rediscluster.addZSet(key,policy.getMessageNo().toString(),policy.getMessageSequenceNo());
            logger.info("set policy operate sequence to  redis  success! msg=" + policy.objectToJson());
            return true;
        }catch(Exception e){
            logger.error("set policy operate sequence to  redis  error! msg=" + policy.objectToJson(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
    }


    /**
     * 将policy信息写入redis通用方法
     * 将策略相关信息写入redis任务队列
     * @param policy
     * @return
     */
    public boolean setPolicyToRedisTask(BaseVO policy){
        if ( policy.getTopTaskId()==null || policy.getTopTaskId()==0){
            return setPolicyToRedisTask(0L,policy.objectToJson());
        }
        return setPolicyToRedisTask(policy.getTopTaskId(),policy.objectToJson());
    }

    /**
     *
     * @Title: setPolicyToRedisTask
     * @Description: 添加策略到redis任务hash中(这里用一句话描述这个方法的作用)
     * @param @param policyJson
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean setPolicyToRedisTask(String policyJson){

        return setPolicyToRedisTask(0L,policyJson);
    }

    /**
     * 添加策略到redis任务hash中
     * @param topTaskId
     * @param policyJson
     * @return
     */
    public boolean setPolicyToRedisTask(long topTaskId, String policyJson){

        long taskId = TaskIdUtil.getInstance().getTaskId();
        if(topTaskId==0){
            topTaskId=taskId;
        }
        return setPolicyToRedisTask(taskId, topTaskId, policyJson);
    }

    /**
     * 添加策略到redis任务hash中
     * @param taskId
     * @param topTaskId
     * @param policyJson
     * @return
     */
    public boolean setPolicyToRedisTask(long taskId, long topTaskId, String policyJson){
        try{
            RedisTaskStatus taskStatus = new RedisTaskStatus();

            //获得重试参数   最大处理次数,过期时间,处理间隔
            String commandStrs = LocalConfig.getInstance().getHashValueByHashKey("policy_dpi");
            String[] params = commandStrs.split(",");
            int maxTimes = Integer.parseInt(params[0]);
            long expireTime = Long.parseLong(params[1]);
            int interval = Integer.parseInt(params[2]);

            taskStatus.setTaskType(4);
            taskStatus.setContent(policyJson);
            taskStatus.setStatus(1);
            taskStatus.setMaxTimes(maxTimes);
            taskStatus.setTimes(1);
            taskStatus.setExpireTime(expireTime);
            taskStatus.setInterval(interval);
            taskStatus.setNextTime(System.currentTimeMillis()/1000 + interval);
            taskStatus.setTaskId(taskId);
            taskStatus.setCreateTime(System.currentTimeMillis()/1000);

            taskStatus.setTopTaskId(topTaskId);
            TaskMessageUtil.getInstance().setTask(taskStatus.getTaskId(), taskStatus);

            logger.info("===========taskId========"+taskStatus.getTaskId());

            return true;
        }catch(Exception e){
            logger.error("set policy to redis task hash error! msg=" + policyJson, e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
    }


    /**
     * 写redis channel下发策略
     * @param message
     * @return
     */
    public boolean publishRedisChannel(StrategySendChannel message) {

        rediscluster.sendMessage(RedisKeyConstant.REDIS_KEY_POLICY_PUBLISH_CHANNEL, message.objectToJson());

        return true;
    }


    /**
     *
     * @Title: publishRedisChannel
     * @Description: 写redis channel下发策略(这里用一句话描述这个方法的作用)
     * @param @param policy
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean publishRedisChannel(BaseVO policy) {
        try{
            if ( policy instanceof DpiDeviceInfoStrategy ) {
                DpiDeviceInfoStrategy dpiDevInfo = (DpiDeviceInfoStrategy)policy;

                //创建policy ack
                List<String> dpiIps = new ArrayList<String>();
                dpiIps.add(dpiDevInfo.getDpiIp());
                setPolicyToRedisAck(policy, dpiIps);

                StrategySendChannel channel = new StrategySendChannel();
                //发布消息
                if (dpiDevInfo.getDpiPort()!=null&&dpiDevInfo.getDpiPort()!=0) {
                    if (dpiDevInfo.getProbeType()==null){
                        dpiDevInfo.setProbeType(DevFlag.DPI.getValue());
                    }

                    channel.setMessageContent(policy.objectToJson());
                    if (dpiDevInfo.getProbeType()==null){
                        channel.setProbeType(DevFlag.DPI.getValue());
                    } else {
                        channel.setProbeType(dpiDevInfo.getProbeType());
                    }

                    channel.setMessageType(policy.getMessageType());

                    rediscluster.sendMessage(RedisKeyConstant.REDIS_KEY_POLICY_PUBLISH_CHANNEL,channel.objectToJson());
                    logger.info("set policy to redis channel success! msg=" + JSON.toJSONString(policy));
                }


            } else {
                //创建policy ack
                List<String> dpiIps = getDpiIpByPolicy(policy);
                setPolicyToRedisAck(policy, dpiIps);

                //发布消息
                StrategySendChannel channel = new StrategySendChannel();
                channel.setProbeType(policy.getProbeType());
                channel.setMessageType(policy.getMessageType());
                channel.setMessageContent(policy.objectToJson());
                rediscluster.sendMessage(RedisKeyConstant.REDIS_KEY_POLICY_PUBLISH_CHANNEL,channel.objectToJson());
                logger.info("set policy to redis channel success! msg=" + JSON.toJSONString(policy));

            }
        }catch(Exception e){
            logger.error("set policy to redis channel error! msg=" + JSON.toJSONString(policy), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }


        return true;
    }


    /**
     *
     * @Title: setPolicyToRedisAck
     * @Description: 策略添加到redis ack中(这里用一句话描述这个方法的作用)
     * @param @param policy
     * @param @param eu_ip
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean setPolicyToRedisAck(BaseVO policy, List<String> eu_ip){

        // 统一设置ack设备类型为DPI类型
        PolicyAckUtil.getInstance().setPolicyAck(ProbeType.DPI.getValue(), policy.getMessageType(), policy.getMessageNo(), eu_ip, 0);
        return true;
    }



    /**
     *
     * @Title: manualRetryPolicy
     * @Description: 手动重发策略(这里用一句话描述这个方法的作用)
     * @param @param probeType
     * @param @param messageType
     * @param @param messageNo
     * @param @param eu_ip
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean manualRetryPolicy(int probeType, int messageType, long messageNo, List<String> eu_ip){
        return manualRetryPolicy(0L, probeType, messageType, messageNo, eu_ip);
    }
    /**
     *
     * @Title: manualRetryPolicy
     * @Description: 手动重发策略(这里用一句话描述这个方法的作用)
     * @param @param probeType
     * @param @param messageType
     * @param @param messageNo
     * @param @param eu_ip
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean manualRetryPolicyTask(long topTaskId,int probeType, int messageType, long messageNo, List<String> eu_ip){
        return manualRetryPolicy(topTaskId, probeType, messageType, messageNo, eu_ip);
    }
    /**
     *
     * @Title: manualRetryPolicy
     * @Description: 手动重发策略(这里用一句话描述这个方法的作用)
     * @param @param topTaskId
     * @param @param probeType
     * @param @param messageType
     * @param @param messageNo
     * @param @param dpiIp
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public boolean manualRetryPolicy(long topTaskId, int probeType, int messageType, long messageNo, List<String> dpiIp){
        try{
            boolean result = false;

            //获得策略信息
            String key = String.format(RedisKeyConstant.REDIS_POLICY_HASH_KEY, probeType, messageType);
            String policyContent = rediscluster.getHash(key, String.valueOf(messageNo));

            //添加到redis task hash中
            result = setPolicyToRedisTask(topTaskId, policyContent);
            if(!result){
                logger.error("policy add redis task hash error! probeType=" + probeType + ", messageType="+messageType + ", messageNo=" + messageNo);
                return false;
            }

            Map<String,Object> maps =  JSON.parseObject(policyContent,new TypeReference<Map<String, Object>>(){});

            int operationType = (Integer) maps.get("operationType");

            if (OperationConstants.OPERATION_UPDATE == operationType){
                maps.put("operationType",1);
            }

            //写redis channel
            StrategySendChannel channel = new StrategySendChannel();
            channel.setIp(dpiIp);
            channel.setProbeType(probeType);
            channel.setMessageType(messageType);
            channel.setMessageContent(JSON.toJSONString(maps));

            result = publishRedisChannel(channel);
            if(!result){
                logger.error("publish policy to redis channel error! probeType=" + probeType + ", messageType="+messageType + ", messageNo=" + messageNo);
                return false;
            }

            return true;
        }catch(Exception e){
            logger.error("manual retry policy error! probeType=" + probeType + ", messageType="+messageType + ", messageNo=" + messageNo, e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
    }


    /**
     *
     * @param policy
     * @return
     */
    public boolean strategySortAndChannelToRedis(BaseVO policy) {
        try{
            // 是否成功标志
            boolean success = setPolicyOperateSequenceToRedis(policy);
            if (!success) {
                logger.error("setPolicyOperateSequenceToRedis failed..."+policy.objectToJson());
                return false;
            }

            success = publishRedisChannel(policy);
            if (!success) {
                logger.error("publishRedisChannel failed..."+policy.objectToJson());
                return false;
            }
        }catch(Exception e){
            logger.error("strategySortAndChannelToRedis error! msg=" + policy.objectToJson(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }

        return true;
    }

    /**
     *
     * @param policy
     * @return
     */
    public boolean addTaskAndChannelToRedis(BaseVO policy){
        try{
            // 是否成功标志
            boolean success = setPolicyToRedisTask(policy);
            if(!success) {
                logger.error("setPolicyToRedisTask failed..."+policy.objectToJson());
                return false;
            }else {
            	logger.info("setPolicyToRedisTask success ... "+policy.objectToJson());
            }
            success = publishRedisChannel(policy);
            if (!success) {
                logger.error("publishRedisChannel failed..."+policy.objectToJson());
                return false;
            }else {
            	logger.info("publishRedisChannel success ... "+policy.objectToJson());
            }
        } catch (Exception e){
            logger.error("addTaskAndChannelToRedis error! msg=" + policy.objectToJson(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }

        return true;
    }

    /**
     * 更新redis相关信息(StrategySorted  jobstatus )并发送通道信息(StrategySendChannel)
     * @param policy
     * @return
     */
    public boolean sendRedisMessage(BaseVO policy){
        try{
            boolean result = false;
            result = setPolicyOperateSequenceToRedis(policy);
            if ( !result ) {
                logger.error("setPolicyOperateSequenceToRedis failed..."+policy.objectToJson());
                return false;
            }else {
            	 logger.info("setPolicyOperateSequenceToRedis success ... "+policy.objectToJson());
            }
            result = addTaskAndChannelToRedis(policy);
            if ( !result ) {
                logger.error("addTaskAndChannelToRedis failed..."+policy.objectToJson());
                return false;
            }else {
            	 logger.info("addTaskAndChannelToRedis success ... "+policy.objectToJson());
            }
        } catch (Exception e){
            logger.error("sendRedisMessage error! msg=" + policy.objectToJson(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
        return true;
    }


    /**
     *
     * @Description: 根据策略获得需要发送的DPI IP(这里用一句话描述这个方法的作用)
     * @param policy
     * @param @return    设定文件
     * @return List<String>    返回类型
     * @throws
     */
    private List<String> getDpiIpByPolicy(BaseVO policy){
        List<String> ipList = new ArrayList<String>();

        try{
            String key = String.format(RedisKeyConstant.REDIS_POLICY_HASH_KEY, ProbeType.DPI.getValue(),
                    MessageTypeConstants.MESSAGE_TYPE_DPI_INFO);

            Map<String, String> dpiList = rediscluster.getHashs(key);


            //添加需要下发的服务器
            for(Entry<String, String> entry : dpiList.entrySet()){
                DpiDeviceInfoStrategy dpiInfo = DpiDeviceInfoStrategy.parseFromJson(entry.getValue(), DpiDeviceInfoStrategy.class);
                //删除状态不需要
                if(dpiInfo.getOperationType() == OperationConstants.OPERATION_DELETE) {
                    continue;
                }
                if (dpiInfo.getDpiIp()==null){
                    continue;
                }
                //是否已经存在
                if(ipList.contains(dpiInfo.getDpiIp())) {
                    continue;
                }

                ipList.add(dpiInfo.getDpiIp());
            }
        } catch(Exception e){
            MonitorStatisticsUtils.addEvent(e);
        }

        return ipList;
    }

}
