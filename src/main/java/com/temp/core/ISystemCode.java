/**
 * @Description : 系統狀態代碼表
 * @ClassName : ISystemCode.java
 * @Copyright : Copyright (c) 2024 HiTRUST All Rights Reserved.
 */
package com.temp.core;

public interface ISystemCode {

	/** 成功狀態碼 */
	public final static String SUCCESS_STATUS_CODE = "0";
	
	/** 未知狀態碼 */
	public final static String UNKNOWN_STATUS_CODE = "9";

	/** 狀態等級-資訊 */
	public final static String SEVERITY_INFO = "INFO";

	/** 狀態等級-警告 */
	public final static String SEVERITY_WARN = "WARN";

	/** 狀態等級-錯誤 */
	public final static String SEVERITY_ERROR = "ERROR";

	/** 狀態等級-逾時 */
	public final static String SEVERITY_TIMEOUT = "TIMEOUT";

	/** 狀態不明 */
	public final static int UNKNOWN = -1;

	/** 交易成功 */
	public final static int SUCCESS = 0;

	/** 交易失敗 */
	public final static int FAIL = 1;
	
}