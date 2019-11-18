package com.revature.exception;

public class MyCustomException extends Exception {
	
	public MyCustomException() {
		
	}
	
	public MyCustomException(String errorMessage) {
        super(errorMessage);
    }
	
	public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
