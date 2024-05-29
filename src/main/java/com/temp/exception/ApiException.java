package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class ApiException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ApiException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public ApiException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public ApiException(Exception exception) {
		super(exception);
	}
	
	public ApiException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public ApiException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public ApiException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public ApiException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public ApiException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public ApiException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public ApiException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public ApiException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
