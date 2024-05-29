package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ServiceException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public ServiceException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public ServiceException(Exception exception) {
		super(exception);
	}
	
	public ServiceException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public ServiceException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public ServiceException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public ServiceException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public ServiceException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public ServiceException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public ServiceException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public ServiceException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
