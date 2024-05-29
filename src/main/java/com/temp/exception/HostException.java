package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class HostException extends BaseException {

	private static final long serialVersionUID = 1L;

	public HostException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public HostException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public HostException(Exception exception) {
		super(exception);
	}
	
	public HostException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public HostException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public HostException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public HostException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public HostException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public HostException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public HostException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public HostException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
