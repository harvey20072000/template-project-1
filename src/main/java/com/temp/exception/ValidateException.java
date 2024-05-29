package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

/**
 * 業務邏輯檢核Exception
 */
public class ValidateException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ValidateException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public ValidateException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public ValidateException(Exception exception) {
		super(exception);
	}
	
	public ValidateException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public ValidateException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public ValidateException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public ValidateException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public ValidateException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public ValidateException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public ValidateException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public ValidateException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
