package com.innovasolutions.validation.rule;

import org.junit.platform.commons.util.StringUtils;

public class PasswordLengthInRange implements Rule {

	private Integer lowerBound;
	private Integer upperBound;

	public PasswordLengthInRange(Integer lowerBound, Integer upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	public Boolean isValid(String password) {
		if (StringUtils.isBlank(password)) {
			return Boolean.FALSE;
		}

		final Integer length = password.trim().length();
		return length >= lowerBound && length <= upperBound;
	}
}
