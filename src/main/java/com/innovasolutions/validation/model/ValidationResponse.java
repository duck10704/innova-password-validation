package com.innovasolutions.validation.model;

import java.io.Serializable;

public class ValidationResponse implements Serializable {

	private static final long serialVersionUID = 4941492023353944899L;

	private Integer statusCode;
	private String message;

	public ValidationResponse(Integer status, String message) {
		this.statusCode = status;
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
