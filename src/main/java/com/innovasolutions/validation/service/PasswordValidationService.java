package com.innovasolutions.validation.service;

import com.innovasolutions.validation.model.ValidationResponse;

public interface PasswordValidationService {

	ValidationResponse isValidPassword(String password);
}
