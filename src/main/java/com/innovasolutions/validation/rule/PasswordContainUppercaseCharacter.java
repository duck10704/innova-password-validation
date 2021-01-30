package com.innovasolutions.validation.rule;

import org.junit.platform.commons.util.StringUtils;

public class PasswordContainUppercaseCharacter implements Rule {

	private static final String REGEX_UPPERCASE_CHARACTER = ".*[A-Z].*";

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		return password.trim().matches(REGEX_UPPERCASE_CHARACTER);
	}
}
