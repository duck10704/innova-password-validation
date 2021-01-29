package com.innovasolutions.validation.controller;

import org.springframework.http.ResponseEntity;

import com.innovasolutions.validation.model.ValidationResponse;

public abstract class AbstractController {

	protected ResponseEntity<ValidationResponse> buildResponseResult(ValidationResponse r) {
		return ResponseEntity.ok().body(r);
	}
}
