/**
 * @Description : Message Code Interface
 * @ClassName : IMsgCode.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.core;

public interface IMsgCode {

	/**
	 * 取得訊息代碼
	 * @return
	 */
	public String getName();
	
	/**
	 * 取得錯誤代碼代碼
	 * @return
	 */
	public String getCode();
	
	/**
	 * 取得訊息說明
	 * @return
	 */
	public String getMemo();
	
}
