package com.innovasolutions.validation.rule;

import org.junit.platform.commons.util.StringUtils;

public class PasswordContainNumericalCharacter implements Rule {

	private static final String REGEX_NUMERICAL_CHARACTER = ".*[0-9].*";

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		return password.trim().matches(REGEX_NUMERICAL_CHARACTER);
	}
}
