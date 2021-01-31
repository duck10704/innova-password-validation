package com.innovasolutions.validation.rule;

import org.apache.commons.lang3.StringUtils;

public class PasswordIsNotBlank implements Rule {

	@Override
	public Boolean isValid(String password) {
		return !StringUtils.isBlank(password);
	}
}
