package com.innovasolutions.validation.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -675122805955358148L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}
}
