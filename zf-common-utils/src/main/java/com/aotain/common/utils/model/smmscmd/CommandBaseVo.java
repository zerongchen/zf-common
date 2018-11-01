package com.aotain.common.utils.model.smmscmd;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 指令基类定义公共的JSON转换方法
 * 
 * @author liuz@aotian.com
 * @date 2017年11月10日 下午1:00:00
 */
public class CommandBaseVo implements Serializable {
	private static final long serialVersionUID = 2210991662170874437L;

	public static <T extends CommandBaseVo> T parseFrom(String json, Class<T> cls) {
		return  JSON.parseObject(json,cls);
	}

	public String toJsonString() {
		return JSON.toJSONString(this);
	}
}
