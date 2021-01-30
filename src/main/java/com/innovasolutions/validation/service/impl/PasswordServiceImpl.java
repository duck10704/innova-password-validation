package com.innovasolutions.validation.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.innovasolutions.validation.model.ValidationException;
import com.innovasolutions.validation.model.ValidationResponse;
import com.innovasolutions.validation.service.PasswordService;

@Component
public class PasswordServiceImpl implements PasswordService {

	private static final String PATTERN_UPPERCASE_CHARACTERS = ".*[A-Z].*";
	private static final String PATTERN_LOWERCASE_CHARACTERS = ".*[a-z].*";
	private static final String PATTERN_NUMERICAL_CHARACTERS = ".*[0-9].*";
	private static final String PATTERN_LOWERCASE_NUMERICAL_CHARACTERS = "[a-z0-9]+";
	private static final String PATTERN_REPEATING_STRING_CHARACTERS = "([a-z0-9]+)\\1+$";

	@Override
	public ValidationResponse isValidPassword(String password) {
		if (StringUtils.isBlank(password)) {
			throw new ValidationException("Password can not be blank");
		}
		if (password.length() < 5 || password.length() > 12) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE, "Password must between 5 and 12 characters length");
		}
		if (isMatchTheRule(PATTERN_UPPERCASE_CHARACTERS, password)) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE, "Password can not contain uppercase character");
		}
		if (!isMatchTheRule(PATTERN_LOWERCASE_CHARACTERS, password)) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE,
					"Password must contain at least one lowercase character");
		}
		if (!isMatchTheRule(PATTERN_NUMERICAL_CHARACTERS, password)) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE,
					"Password must contain at least one mumerical character");
		}
		if (!isMatchTheRule(PATTERN_LOWERCASE_NUMERICAL_CHARACTERS, password)) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE,
					"Password must consist of a mixture of lowercase letters and numerical digits only");
		}
		if (isMatchTheRule(PATTERN_REPEATING_STRING_CHARACTERS, password)) {
			return buildPasswordResponse(HttpStatus.NOT_ACCEPTABLE,
					"Password can not contain any repeating substrings");
		}

		return buildPasswordResponse(HttpStatus.OK, "Valid password");
	}

	private Boolean isMatchTheRule(String regex, String password) {
		return password.matches(regex);
	}

	private ValidationResponse buildPasswordResponse(HttpStatus status, String message) {
		return new ValidationResponse(status.name(), message);
	}
}
