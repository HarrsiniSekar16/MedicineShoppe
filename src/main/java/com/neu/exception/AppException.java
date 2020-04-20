package com.neu.exception;

public class AppException extends Exception {
	
	public AppException(String msg) {
		super(msg);
	}

	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}


}
