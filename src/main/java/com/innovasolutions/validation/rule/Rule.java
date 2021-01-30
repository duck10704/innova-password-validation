package com.innovasolutions.validation.rule;

import org.springframework.stereotype.Component;

@Component
public interface Rule {

	Boolean isValid(String password);
}
