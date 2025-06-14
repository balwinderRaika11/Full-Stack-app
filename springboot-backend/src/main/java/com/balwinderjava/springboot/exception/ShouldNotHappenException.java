package com.balwinderjava.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShouldNotHappenException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ShouldNotHappenException(String message) {
		super(message);
	}

	public ShouldNotHappenException(String message, Throwable cause) {
		super(message, cause);
	}
}
