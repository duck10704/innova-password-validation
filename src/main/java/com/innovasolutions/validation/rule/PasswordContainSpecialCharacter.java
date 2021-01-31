package com.innovasolutions.validation.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

public class PasswordContainSpecialCharacter implements Rule {

	private static final String REGEX_SPECIAL_CHARACTER = "[^a-zA-Z0-9]";

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		final Pattern p = Pattern.compile(REGEX_SPECIAL_CHARACTER);
		final Matcher m = p.matcher(password.trim());
		return m.find();
	}
}
