package com.innovasolutions.validation.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.innovasolutions.validation.model.ValidationException;
import com.innovasolutions.validation.model.ValidationResponse;
import com.innovasolutions.validation.rule.PasswordContainLowercaseCharacter;
import com.innovasolutions.validation.rule.PasswordContainNumericalCharacter;
import com.innovasolutions.validation.rule.PasswordContainRepeatingString;
import com.innovasolutions.validation.rule.PasswordContainSpecialCharacter;
import com.innovasolutions.validation.rule.PasswordContainUppercaseCharacter;
import com.innovasolutions.validation.rule.PasswordIsNotBlank;
import com.innovasolutions.validation.rule.PasswordLengthInRange;
import com.innovasolutions.validation.service.PasswordValidationService;

@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public ValidationResponse getPasswordValidationResponse(String password) {
		if (!isPasswordNotBlank(password)) {
			throw new ValidationException(
					messageSource.getMessage("password.validation.is.blank", null, Locale.ENGLISH));
		}
		if (isPasswordContainSpecialCharacter(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.contain.special", null, Locale.ENGLISH));
		}
		if (isPasswordContainUppercase(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.contain.uppercase", null, Locale.ENGLISH));
		}
		if (!isPasswordLengthBetween5And12(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.length.between.5and12.range", null, Locale.ENGLISH));
		}
		if (!isPasswordContainLowercase(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.at.least.one.lowercase", null, Locale.ENGLISH));
		}
		if (!isPasswordContainNumerical(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.at.least.one.numerical", null, Locale.ENGLISH));
		}
		if (isPasswordContainRepeatingString(password)) {
			return buildResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					messageSource.getMessage("password.validation.contain.repeating.substring", null, Locale.ENGLISH));
		}

		return buildResponse(HttpStatus.OK.value(),
				messageSource.getMessage("password.validation.valid", null, Locale.ENGLISH));
	}

	public Boolean isPasswordNotBlank(String password) {
		return new PasswordIsNotBlank().isValid(password);
	}

	public Boolean isPasswordLengthBetween5And12(String password) {
		return new PasswordLengthInRange(5, 12).isValid(password);
	}

	public Boolean isPasswordContainSpecialCharacter(String password) {
		return new PasswordContainSpecialCharacter().isValid(password);
	}

	public Boolean isPasswordContainUppercase(String password) {
		return new PasswordContainUppercaseCharacter().isValid(password);
	}

	public Boolean isPasswordContainLowercase(String password) {
		return new PasswordContainLowercaseCharacter().isValid(password);
	}

	public Boolean isPasswordContainNumerical(String password) {
		return new PasswordContainNumericalCharacter().isValid(password);
	}

	public Boolean isPasswordContainRepeatingString(String password) {
		return new PasswordContainRepeatingString().isValid(password);
	}

	private ValidationResponse buildResponse(int value, String message) {
		return new ValidationResponse(value, message);
	}
}
