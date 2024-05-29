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
	public BaseException(IMsgCode msgCode) {
		super(String.format("%s - %s", msgCode.getCode(), msgCode.getMemo()));
		this.msgCode = msgCode;
	}
	
	public BaseException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public BaseException(Exception exception) {
		super(exception);
	}
	
	public BaseException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public BaseException(IMsgCode msgCode, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
	}
	
	public BaseException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
		this.msgCode = msgCode;
	}
	
	public BaseException(IMsgCode msgCode, Object resObject) {
		super(msgCode.getCode());
		this.msgCode = msgCode;
		this.resObject = resObject;
	}
	
	public BaseException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.resObject = resObject;
	}
	
	public BaseException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode.getCode());
		this.msgCode = msgCode;
		this.status = status;
	}

	public BaseException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.status = status;
	}
	
	public BaseException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode.getCode(), exception);
		this.msgCode = msgCode;
		this.status = status;
		this.resObject = resObject;
	}
	
	// ========== Getters ==========
	public IMsgCode getMsgCode() {
		return msgCode;
	}

	public SystemStatus getStatus() {
		return status;
	}
	
	public Object getResObject() {
		return resObject;
	}
}
