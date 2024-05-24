/**
 * @Description : 系統基礎訊息代碼
 * @ClassName : BaseMsgCode.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.core;

public enum BaseMsgCode implements IMsgCode {
	
	// ========== System ==========
	API_0000("API_0000", "服務處理完成。"),
	API_SYS99("API_SYS99", "系統處理異常，請洽系管理員。"),
	// ========== System ==========
	
	// ========== Database ==========
	DB_ER001("DB_ER001", "資料庫新增異常，請稍後再試！"),
	DB_ER002("DB_ER002", "資料庫查詢異常，請稍後再試！"),
	DB_ER003("DB_ER003", "服務處理完成。"),
	
	DB_WAN001("DB_WAN001", "查無符合條件資料"),
	
	FLOW_ER001("FLOW_ER001", "產生交易審核流程物件失敗"),
	FLOW_ER002("FLOW_ER002", "產生交易審核流程物件參數錯誤"),
	
	FLOW_ER900("FLOW_ER900", "登入密碼驗證失敗"),
	FLOW_ER910("FLOW_ER910", "OTP(PUSH)驗證失敗"),
	FLOW_ER920("FLOW_ER920", "GOTP驗證失敗"),
	FLOW_ER930("FLOW_ER990", "PKI驗證失敗");
	
	// ========== Database ==========
	
	private String errorCode;
	private String memo;

	/**
	 * @param msgCode
	 * @param memo
	 */
	private BaseMsgCode(String errorCode, String memo) {
		this.errorCode = errorCode;
		this.memo = memo;
	}

	// ========== Getter & Setter ==========
	@Override
	public String getName() {
		return super.name();
	}
	
	@Override
	public String getCode() {
		return this.errorCode;
	}

	@Override
	public String getMemo() {
		return this.memo;
	}
}
