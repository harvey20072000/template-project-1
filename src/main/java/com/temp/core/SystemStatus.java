/**
 * @Description : 系統狀態
 * @ClassName : SystemStatus.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.core;

import org.apache.commons.lang3.StringUtils;

public class SystemStatus {
	
	/** 系統代碼 */
	protected String systemId = "";

	/** 交易回應代碼，0：表示交易正確 */
	protected String statusCode = ISystemCode.UNKNOWN_STATUS_CODE;

	/** 狀態等級 ERROR/WARNING/INFO/TIMEOUT */
	protected String severity = "";

	/** 狀態描述 */
	protected String statusDesc = "";

	// ========== Constructors ==========
	/**
	 * Constructor
	 */
	public SystemStatus() {
		super();
	}

	/**
	 * @param systemId
	 * @param statusCode
	 * @param severity
	 * @param statusDesc
	 */
	public SystemStatus(String systemId, String statusCode, String severity, String statusDesc) {
		super();
		this.systemId = systemId;
		this.statusCode = statusCode;
		this.severity = severity;
		this.statusDesc = statusDesc;
	}

	// ========== public Methods ==========
	/**
	 * 是否為成功的狀態
	 * @return
	 */
	public boolean isSuccess() {
		if (StringUtils.isBlank(this.statusCode)) {
			return true;
		} else {
			return this.statusCode.equalsIgnoreCase(ISystemCode.SUCCESS_STATUS_CODE);
		}
	}
	
	/**
	 * 是否為未知的狀態
	 * @return
	 */
	public boolean isUnknown() {
		return getStatusCode().equalsIgnoreCase(ISystemCode.UNKNOWN_STATUS_CODE);
	}

	/**
	 * 是否為 Timeout
	 * @return
	 */
	public boolean isTimeout() {
		return getSeverity().equalsIgnoreCase(ISystemCode.SEVERITY_TIMEOUT);
	}
	
	/**
	 * 傳回狀態結果
	 * @return
	 */
	public int getResult() {
		int rsltCode = ISystemCode.UNKNOWN;
		
		if (isSuccess()) {
			rsltCode = ISystemCode.SUCCESS;
		} else if (isUnknown()) {
			rsltCode = ISystemCode.FAIL;
		} else {
			rsltCode = ISystemCode.FAIL;
		}
		
		return rsltCode;
	}
	
	// ========== Getters & Setters ==========
	/**
	 * @return the systemId
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}
