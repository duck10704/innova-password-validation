package com.innovasolutions.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovasolutions.validation.model.Password;
import com.innovasolutions.validation.model.ValidationResponse;
import com.innovasolutions.validation.service.PasswordService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PasswordValidationController extends AbstractController {

	@Autowired
	private PasswordService passwordService;

	@PostMapping("/password/validation")
	public ResponseEntity<ValidationResponse> passwordValidation(@RequestBody Password password) {
		final ValidationResponse r = passwordService.isValidPassword(password.getPassword());
		return buildResponseResult(r);
	}
}
