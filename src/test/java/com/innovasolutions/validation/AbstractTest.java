package com.innovasolutions.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.innovasolutions.validation.service.PasswordValidationService;

public abstract class AbstractTest {

	protected final static String PASSWORD_VALIDATION_URL = "/password/validation";
	protected final static String ALPHABETIC_LOWERCASE = "abcdefghijklmnopqrstuvxyz";
	protected final static String ALPHABETIC_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected final static String NUMERICAL = "0123456789";

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected PasswordValidationService passwordValidationService;

	protected String genRandomLowercaseString(Integer stringLength) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			int index = (int) (ALPHABETIC_LOWERCASE.length() * Math.random());
			sb.append(ALPHABETIC_LOWERCASE.charAt(index));
		}

		return sb.toString();
	}

	protected String genRandomUppercaseString(Integer stringLength) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			int index = (int) (ALPHABETIC_UPPERCASE.length() * Math.random());
			sb.append(ALPHABETIC_UPPERCASE.charAt(index));
		}

		return sb.toString();
	}

	protected String genRandomNumericalString(Integer stringLength) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			int index = (int) (NUMERICAL.length() * Math.random());
			sb.append(NUMERICAL.charAt(index));
		}

		return sb.toString();
	}

	protected String genRandomMixString(Integer stringLength) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			int index = (int) ((ALPHABETIC_LOWERCASE + ALPHABETIC_UPPERCASE + NUMERICAL).length() * Math.random());
			sb.append((ALPHABETIC_LOWERCASE + ALPHABETIC_UPPERCASE + NUMERICAL).charAt(index));
		}

		return sb.toString();
	}
}
