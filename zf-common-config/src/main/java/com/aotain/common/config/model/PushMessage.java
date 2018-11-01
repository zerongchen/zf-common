package com.aotain.common.config.model;

import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.persistence.Transient;

/**
 * 推送信息
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午11:09:31
 */
@Setter
@Getter
public class PushMessage {
	private Long pushId;
	private Integer pushType; // 调用PushClient.pushAlarmMessage方法时，只需要提供sendData。1：邮件类型推送；2：短信类型推送；3：微信类型推送；
	private Integer subjectType;//主题类型，1=系统，2=拨测
	private String alarmMessage;//告警消息
	private String mailSubject;//邮件主题
	private String pushContent;//推送内容
	private Integer pushStatus;//推送状态，0-未推送、1-推送中、2-推送成功、3-推送失败'
	private Integer repushTimes;//重发次数
	private String serverIp;//调用服务器
	private Date createTime;  // 创建时间
	private Date updateTime;  // 修改时间
	@Transient
    private String blobAlarmMessageJson;
	@Transient
    private String blobPushContentJson;

    public void setAlarmMessage(String alarmMessage) {

        this.alarmMessage = alarmMessage;
        String blob = "";
        try {
            blob = new String(alarmMessage.getBytes("iso-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.blobAlarmMessageJson = blob;
    }
    
    public void setPushContent(String pushContent) {

        this.pushContent = pushContent;
        String blob = "";
        try {
            blob = new String(pushContent.getBytes("iso-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.blobPushContentJson = blob;
    }
}
