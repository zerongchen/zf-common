package com.aotain.common.utils.model.smmsupload;

import java.math.BigInteger;

public class VirtualServerInfo {

	private long virtualId;
	private String virtualhostName;
	private BigInteger virtualhostState;
	private BigInteger virtualhostType;
	private String virtualhostAddress;
	private String virtualhostManagementAddress;

	public long getVirtualId() {
		return virtualId;
	}

	public void setVirtualId(long virtualId) {
		this.virtualId = virtualId;
	}

	public String getVirtualhostName() {
		return virtualhostName;
	}

	public void setVirtualhostName(String virtualhostName) {
		this.virtualhostName = virtualhostName;
	}

	public BigInteger getVirtualhostState() {
		return virtualhostState;
	}

	public void setVirtualhostState(BigInteger virtualhostState) {
		this.virtualhostState = virtualhostState;
	}

	public BigInteger getVirtualhostType() {
		return virtualhostType;
	}

	public void setVirtualhostType(BigInteger virtualhostType) {
		this.virtualhostType = virtualhostType;
	}

	public String getVirtualhostAddress() {
		return virtualhostAddress;
	}

	public void setVirtualhostAddress(String virtualhostAddress) {
		this.virtualhostAddress = virtualhostAddress;
	}

	public String getVirtualhostManagementAddress() {
		return virtualhostManagementAddress;
	}

	public void setVirtualhostManagementAddress(
			String virtualhostManagementAddress) {
		this.virtualhostManagementAddress = virtualhostManagementAddress;
	}

}
