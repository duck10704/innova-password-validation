package com.innovasolutions.validation.rule;

import org.junit.platform.commons.util.StringUtils;

public class PasswordContainLowercaseCharacter implements Rule {

	private static final String REGEX_LOWERCASE_CHARACTER = ".*[a-z].*";

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		return password.trim().matches(REGEX_LOWERCASE_CHARACTER);
	}
}
