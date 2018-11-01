package com.aotain.common.config.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.common.config.model.PushMessage;

@MyBatisDao
public interface PushMapper {
	
	/**
	 * 更新推送状态
	 * 
	 * @param pushId
	 * @param status 0-未推送，1-推送中，2-推送成功，3-推送失败
	 * @return
	 */
	public int updatePushMessageStatus(PushMessage record);
	
	public PushMessage getPushMessageById(Long id);

	public int insert(PushMessage record);
	
	public String getDeployProvince(String shortProvice);
}
