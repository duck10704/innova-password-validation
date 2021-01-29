package com.innovasolutions.validation.model;

import java.io.Serializable;

public class ValidationResponse implements Serializable {

	private static final long serialVersionUID = 4941492023353944899L;

	private String status;
	private String message;

	public ValidationResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
