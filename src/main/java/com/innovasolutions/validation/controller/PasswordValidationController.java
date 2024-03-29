package com.innovasolutions.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovasolutions.validation.model.ValidationResponse;
import com.innovasolutions.validation.service.PasswordValidationService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PasswordValidationController {

	@Autowired
	private PasswordValidationService passwordValidationService;

	@PostMapping("/password/validation")
	public ResponseEntity<ValidationResponse> passwordValidation(@RequestBody String password) {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse(password);
		return ResponseEntity.ok().body(r);
	}
}
