package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class UtilException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UtilException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public UtilException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public UtilException(Exception exception) {
		super(exception);
	}
	
	public UtilException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public UtilException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public UtilException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public UtilException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public UtilException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public UtilException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public UtilException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public UtilException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
