package com.temp.exception;

import com.temp.core.IMsgCode;
import com.temp.core.SystemStatus;

public class DBException extends BaseException {

	private static final long serialVersionUID = 1L;

	public DBException(IMsgCode msgCode) {
		super(msgCode);
	}
	
	public DBException(String errorMsgs) {
		super(errorMsgs);
	}
	
	public DBException(Exception exception) {
		super(exception);
	}
	
	public DBException(String errorMsgs, Exception exception) {
		super(errorMsgs, exception);
	}

	public DBException(IMsgCode msgCode, Exception exception) {
		super(msgCode, exception);
	}
	
	public DBException(IMsgCode msgCode, String errorMsgs, Exception exception) {
		super(msgCode, errorMsgs, exception);
	}
	
	public DBException(IMsgCode msgCode, Object resObject) {
		super(msgCode, resObject);
	}
	
	public DBException(IMsgCode msgCode, Object resObject, Exception exception) {
		super(msgCode, resObject, exception);
	}
	
	public DBException(IMsgCode msgCode, SystemStatus status) {
		super(msgCode, status);
	}

	public DBException(IMsgCode msgCode, SystemStatus status, Exception exception) {
		super(msgCode, status, exception);
	}
	
	public DBException(IMsgCode msgCode, SystemStatus status, Object resObject, Exception exception) {
		super(msgCode, status, resObject, exception);
	}

}
