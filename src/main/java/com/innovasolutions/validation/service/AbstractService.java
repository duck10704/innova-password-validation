package com.innovasolutions.validation.service;

import org.springframework.http.HttpStatus;

import com.innovasolutions.validation.model.ValidationResponse;

public abstract class AbstractService {

	protected Boolean isMatchTheRule(String regex, String password) {
		return password.matches(regex);
	}

	protected ValidationResponse buildPasswordResponse(HttpStatus status, String message) {
		return new ValidationResponse(status.name(), message);
	}
}
