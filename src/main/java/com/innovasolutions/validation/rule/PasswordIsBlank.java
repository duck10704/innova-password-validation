package com.innovasolutions.validation.rule;

import org.apache.commons.lang3.StringUtils;

public class PasswordIsBlank implements Rule {

	@Override
	public Boolean isValid(String password) {
		return StringUtils.isNotBlank(password);
	}
}
