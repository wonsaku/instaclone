package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	

	private Map<String, String> errorMap;
	public CustomApiException(String message) {
		super(message);
	}
	
}
