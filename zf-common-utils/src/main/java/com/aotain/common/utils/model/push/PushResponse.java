package com.aotain.common.utils.model.push;

/**
 * 推送结果调用返回
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 下午6:03:06
 */
public class PushResponse {
	// 0——处理完成；1——文件解密失败；2——文件校验失败；
	// 3——文件解压缩失败； 4——文件格式异常； 5——文件内容异常； 900——其他异常
	private Integer resultCode;

	private String msg;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "PushResponse [resultCode=" + resultCode + ", msg=" + msg + "]";
	}

}
