package com.aotain.common.utils.model.push;

import com.aotain.common.utils.push.PushSecurityTool.PushEncryptResult;
import lombok.Getter;
import lombok.Setter;

/**
 * 推送接口信息
 *
 * @author liuz@aotian.com
 * @date 2017年8月4日 下午6:08:15
 */
@Setter
@Getter
public class PushInterfaceMessage {
	private String randVal;
	private String pwdHash;
	private String push;
	private String pushHash;
	private int encryptAlgorithm;
	private int hashAlgorithm;
	private int compressionFormat;
	private String pushVersion;

	private int pushType; // 1-邮件，2-短信，3-微信，0-全部
	private long pushSequence; // 本次推送唯一序列

	public PushInterfaceMessage(PushEncryptResult ers, Integer pushType) {
		randVal = ers.getRandVal();
		pwdHash = ers.getPwdHash();
		push = ers.getPush();
		pushHash = ers.getPushHash();
		encryptAlgorithm = ers.getEncryptAlgorithm();
		hashAlgorithm = ers.getHashAlgorithm();
		compressionFormat = ers.getCompressionFormat();
		pushVersion = ers.getPushVersion();
		this.pushType = pushType;
		pushSequence = System.currentTimeMillis();
	}

	public PushInterfaceMessage() {
	}

	@Override
	public String toString() {
		return "PushInterfaceMessage [randVal=" + randVal + ", pwdHash=" + pwdHash + ", push=" + push + ", pushHash=" + pushHash + ", encryptAlgorithm=" + encryptAlgorithm + ", hashAlgorithm="
				+ hashAlgorithm + ", commpresssionFormat=" + compressionFormat + ", pushVersion=" + pushVersion + ", pushType=" + pushType + ", pushSequence=" + pushSequence + "]";
	}

}
