package com.innovasolutions.validation.rule;

import org.junit.platform.commons.util.StringUtils;

public class PasswordContainRepeatingString implements Rule {

	private static final String REGEX_REPEATING_STRING = "([a-zA-Z0-9]+)\\1+$";

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		return password.trim().matches(REGEX_REPEATING_STRING);
	}
}
