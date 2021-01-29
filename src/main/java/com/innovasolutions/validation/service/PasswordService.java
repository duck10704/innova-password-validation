package com.innovasolutions.validation.service;

import org.springframework.stereotype.Service;

import com.innovasolutions.validation.model.ValidationResponse;

@Service
public interface PasswordService {

	ValidationResponse isValidPassword(String password);
}
