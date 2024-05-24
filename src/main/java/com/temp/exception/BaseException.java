package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	// ========== Base Exception Attributes ==========
	/** Message Code */
	private IMsgCode msgCode; 

	/** 系統狀態 */
	private SystemStatus status;
	
	/** Response Content */
	private Object resObject;
	
	// ========== Constructors ==========
	/**
	 * @param msgCode
	 */
	public BaseException(IMsgCode msgCode) {
		super(msgCode.getCode());
		this.msgCode = msgCode;
	}
	
	public BaseException(Exception exception) {
		super(exception);
	}
	
	public BaseException(String errorMsgs, Exception exception) {
		super(exception);
	}

	/**
	 * @param msgCode
	 * @param exception
	 */
	public BaseException(IMsgCode msgCode, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
	}
	
	/**
	 * @param msgCode
	 * @param errorMsgs
	 * @param exception
	 */
	public BaseException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
		this.msgCode = msgCode;
	}
	
	/**
	 * @param msgCode
	 * @param resObject
	 */
	public BaseException(IMsgCode msgCode, Object resObject) {
		super(msgCode.getCode());
		this.msgCode = msgCode;
		this.resObject = resObject;
	}
	
	/**
	 * @param msgCode
	 * @param resObject
	 * @param exception
	 */
	public BaseException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.resObject = resObject;
	}
	
	// ========== For IBM 架構使用 ==========
	/**
	 * @param msgCode
	 * @param status
	 */
	public BaseException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode.getCode());
		this.msgCode = msgCode;
		this.status = status;
	}

	/**
	 * @param msgCode
	 * @param status
	 * @param exception
	 */
	public BaseException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.status = status;
	}
	
	/**
	 * @param msgCode
	 * @param status
	 * @param resObject
	 * @param exception
	 */
	public BaseException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.status = status;
		this.resObject = resObject;
	}
	
	// ========== Getters ==========
	/**
	 * @return the msgCode
	 */
	public IMsgCode getMsgCode() {
		return msgCode;
	}

	/**
	 * @return the status
	 */
	public SystemStatus getStatus() {
		return status;
	}
	
	/**
	 * @return the resObject
	 */
	public Object getResObject() {
		return resObject;
	}
}
