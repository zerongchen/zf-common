package com.aotain.common.utils.push;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aotain.common.config.constant.ConfigRedisConstant;
import com.aotain.common.config.dao.PushMapper;
import com.aotain.common.config.model.PushMessage;
import com.aotain.common.config.redis.BaseRedisServiceImpl;
import com.aotain.common.utils.constant.RedisKeyConstant;
import com.aotain.common.utils.model.push.EmailPushMessageRecord;
import com.aotain.common.utils.model.push.PushInterfaceMessage;
import com.aotain.common.utils.model.push.PushMessageRecord;
import com.aotain.common.utils.model.push.PushReceiver;
import com.aotain.common.utils.model.push.PushResponse;
import com.aotain.common.utils.model.push.SendData;
import com.aotain.common.utils.model.push.WeChatSendData;
import com.aotain.common.utils.model.push.WechatPushMessageRecord;
import com.aotain.common.utils.push.PushSecurityTool.PushEncryptResult;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.common.utils.tools.Tools;


@Service
public class PushClient {
	// 推送接口URL
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private BaseRedisServiceImpl<String, String, String> redisService;
	@Autowired
	private PushMapper pushMapper;
	private Logger logger = LoggerFactory.getLogger(PushClient.class);

	/**
	 * 
	* @Title: pushMessage
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param record
	* @return void
	* @throws
	 */
	 
	public void pushMessage(SendData record) {
		String pushType = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_TYPE);
		String shortProvice  = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PROVINCE_AREA);
		String province =  pushMapper.getDeployProvince(shortProvice);
		if(province==null || province.isEmpty()) {
			logger.error("部署省份为空，将不予推送:" + record);
		}else{
			record.setProvince("【"+province+"】");
			if(pushType!=null && !"".equals(pushType)) {
				String[] pushtypes = pushType.split(",");
				for(String str:pushtypes) {
					PushMessage messageVo = new PushMessage();
					messageVo.setServerIp(Tools.getHostAddress().toString());
					messageVo.setCreateTime(new Date());
					messageVo.setUpdateTime(messageVo.getCreateTime());
					messageVo.setAlarmMessage(JSON.toJSONString(record));
					if(str.equals("1")) {
						messageVo.setMailSubject("【系统告警】");
					}
					messageVo.setPushContent(JSON.toJSONString(record));
					messageVo.setPushStatus(1);
					messageVo.setRepushTimes(0);
					messageVo.setPushType(Integer.valueOf(str));
					messageVo.setSubjectType(1);
					pushMapper.insert(messageVo);
					messageVo.setBlobAlarmMessageJson(messageVo.getAlarmMessage());
					messageVo.setBlobPushContentJson(messageVo.getPushContent());
					PushMessageRecord resultRecord = buildRecord(Integer.valueOf(str),messageVo);
					if (resultRecord.getReceiver() == null || resultRecord.getReceiver().isEmpty()) {
						logger.error("收件人为空，将不予推送:" + messageVo);
						messageVo.setPushStatus(3);
						pushMapper.updatePushMessageStatus(messageVo);
						continue;
					}
					try {
						// 将推送记录打包处理：压缩，加密
						PushSecurityTool tools = new PushSecurityTool();
						PushEncryptResult ers = tools.encrypt(JSON.toJSONString(resultRecord));
						// 组装接口信息，并调用推送接口
						PushInterfaceMessage pushMsg = new PushInterfaceMessage(ers, resultRecord.getPushType());
						try {
							PushResponse res = pushMessage(pushMsg);
							if (res == null) {
								messageVo.setPushStatus(3);
								pushMapper.updatePushMessageStatus(messageVo);
								logger.error("推送服务异常或者网络异常：" + messageVo);
							} else {
								//  更新任务状态
								if (res.getResultCode() == 0) {
									messageVo.setPushStatus(1);
									pushMapper.updatePushMessageStatus(messageVo);
									logger.info("推送请求已发送：" + messageVo);
								} else {
									messageVo.setPushStatus(3);
									pushMapper.updatePushMessageStatus(messageVo);
									logger.error("推送请求失败,resultCode:" + res.getResultCode() + ",msg:" + res.getMsg() + ",data="
											+ messageVo);
								}
							}
						} catch (IOException e) {
							messageVo.setPushStatus(3);
							pushMapper.updatePushMessageStatus(messageVo);
							logger.error("推送请求发送失败", e);
							MonitorStatisticsUtils.addEvent(e);
						}
					} catch (Exception e) {
						logger.error("推送请求发送失败", e);
						MonitorStatisticsUtils.addEvent(e);
					}
				}
				
			}else {
				logger.warn("推送类型不存在放弃推送此条告警：" + record.toString());
			}
		}
	}
	
	/**
	 * 
	* @Title: pushMessage
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param record
	* @return void
	* @throws
	 */
	 
	public void pushMessage(Long pushId) {
		String pushType = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_TYPE);
		if(pushType!=null) {
			String[] pushtypes = pushType.split(",");
			for(String str:pushtypes) {
				if(pushId!=null && pushId!=0) {
					PushMessage message = pushMapper.getPushMessageById(pushId);
					if(message==null) {
						logger.error("推送记录不存在，放弃推送此条信息：pushId-"+pushId);
						continue;
					}
					message.setRepushTimes(message.getRepushTimes()+1);
					PushMessageRecord resultRecord = buildRecord(Integer.valueOf(str),message);
					if (resultRecord.getReceiver() == null || resultRecord.getReceiver().isEmpty()) {
						logger.error("收件人为空，将不予推送:" + message);
						message.setPushStatus(3);
						pushMapper.updatePushMessageStatus(message);
						continue;
					}
					try {
						// 将推送记录打包处理：压缩，加密
						PushSecurityTool tools = new PushSecurityTool();
						PushEncryptResult ers = tools.encrypt(JSON.toJSONString(resultRecord));
						// 组装接口信息，并调用推送接口
						PushInterfaceMessage pushMsg = new PushInterfaceMessage(ers, resultRecord.getPushType());
						try {
							PushResponse res = pushMessage(pushMsg);
							if (res == null) {
								message.setPushStatus(3);
								pushMapper.updatePushMessageStatus(message);
								logger.error("推送服务异常或者网络异常：" + message);
							} else {
								//  更新任务状态
								if (res.getResultCode() == 0) {
									message.setPushStatus(1);
									pushMapper.updatePushMessageStatus(message);
									logger.info("推送请求已发送：" + message);
								} else {
									message.setPushStatus(3);
									pushMapper.updatePushMessageStatus(message);
									logger.error("推送请求失败,resultCode:" + res.getResultCode() + ",msg:" + res.getMsg() + ",data="
											+ message);
								}
							}
						} catch (IOException e) {
							message.setPushStatus(3);
							pushMapper.updatePushMessageStatus(message);
							logger.error("推送请求发送失败", e);
							MonitorStatisticsUtils.addEvent(e);
						}
					} catch (Exception e) {
						logger.error("推送请求发送失败", e);
						MonitorStatisticsUtils.addEvent(e);
					}
				}else {
					logger.warn("推送记录ID不存在放弃推送此条告警：" + pushId.toString());
					continue;
				}
			}
		}
	}

	/**
	 * 构造需要推送的消息对象
	 * 
	 * @return
	 */
	private PushMessageRecord buildRecord(int ptype,PushMessage message) {
		switch (ptype) {
		case 1: // Email
			EmailPushMessageRecord record = new EmailPushMessageRecord();
			record.setReturnAddress(redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_URL_ACK));
			record.setPushId(message.getPushId());
			record.setPushType(ptype);
			String mails = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_MAIL_RECEIVER);
			PushReceiver receiver = new PushReceiver();
			List<String> mailrev = Arrays.asList(mails.split(","));
			receiver.setEmailReceiver(mailrev);
			record.setReceiver(receiver);
			record.setTimeStamp(SDF.format(new Date())); // 上报时间戳
			record.setSubject(message.getMailSubject());
			record.setSendData(message.getBlobAlarmMessageJson());
			return record;
		case 2: // wechat
			WechatPushMessageRecord record2 = new WechatPushMessageRecord();
			record2.setReturnAddress(redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_URL_ACK));
			record2.setPushId(message.getPushId());
			record2.setPushType(ptype);
			String wechats = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT,RedisKeyConstant.PUSH_WECHAT_RECEIVER);
			PushReceiver receiverWechat = new PushReceiver();
			List<String> wechatrev = Arrays.asList(wechats.split(","));
			receiverWechat.setDepartmentId(wechatrev);
			record2.setReceiver(receiverWechat);
			record2.setTimeStamp(SDF.format(new Date())); // 上报时间戳
			WeChatSendData tmp = new WeChatSendData();
			// 微信推送类型：text（文本）、image（图片）、voice（声音）、video（视频）、file（文件）、
			// textcard（文本卡片）、news（图文）[暂时只支持text]
			tmp.setMsgType("text");
			tmp.setContent(message.getBlobPushContentJson());
			record2.setSendData(tmp);
			return record2;
		default:
			return null;
		}
	}
	
	/**
	 * 调用推送服务
	 * 
	 * @param pushMsg
	 * @throws IOException
	 */
	private PushResponse pushMessage(PushInterfaceMessage pushMsg) throws IOException {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		NameValuePair v1 = new BasicNameValuePair("randVal", pushMsg.getRandVal());
		NameValuePair v2 = new BasicNameValuePair("pwdHash", pushMsg.getPwdHash());
		NameValuePair v3 = new BasicNameValuePair("push", pushMsg.getPush());
		NameValuePair v4 = new BasicNameValuePair("pushHash", pushMsg.getPushHash());
		NameValuePair v5 = new BasicNameValuePair("encryptAlgorithm", String.valueOf(pushMsg.getEncryptAlgorithm()));
		NameValuePair v6 = new BasicNameValuePair("hashAlgorithm", String.valueOf(pushMsg.getHashAlgorithm()));
		NameValuePair v7 = new BasicNameValuePair("compressionFormat", String.valueOf(pushMsg.getCompressionFormat()));
		NameValuePair v8 = new BasicNameValuePair("pushType", String.valueOf(pushMsg.getPushType()));
		NameValuePair v9 = new BasicNameValuePair("pushSequence", String.valueOf(pushMsg.getPushSequence()));
		NameValuePair v10 = new BasicNameValuePair("pushVersion", pushMsg.getPushVersion());

		formparams.add(v1);
		formparams.add(v2);
		formparams.add(v3);
		formparams.add(v4);
		formparams.add(v5);
		formparams.add(v6);
		formparams.add(v7);
		formparams.add(v8);
		formparams.add(v9);
		formparams.add(v10);
		String PUSH_URL = redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT, RedisKeyConstant.PUSH_URL);
		if(PUSH_URL!=null && !"".equals(PUSH_URL)) {
			String rs = com.aotain.common.utils.tools.HttpUtils.postRequest(PUSH_URL, formparams, "UTF-8");
			try {
				return JSON.parseObject(rs, PushResponse.class);
			} catch (Exception e) {
				logger.error("json转换异常:" + rs, e);
				MonitorStatisticsUtils.addEvent(e);
				return null;
			}
		}else {
			PushResponse response = new PushResponse();
			response.setMsg("推送接口URL错误");
			response.setResultCode(900);
			return response;
		}
		
		
	}
	
}
