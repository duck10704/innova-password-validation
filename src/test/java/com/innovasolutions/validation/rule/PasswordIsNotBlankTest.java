package com.innovasolutions.validation.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordIsNotBlankTest {

	private PasswordIsNotBlank passwordIsNotBlank;

	@Before
	public void before() {
		passwordIsNotBlank = new PasswordIsNotBlank();
	}

	@After
	public void after() {
		passwordIsNotBlank = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordIsNotBlank.isValid(null));
		assertFalse(passwordIsNotBlank.isValid(""));
		assertFalse(passwordIsNotBlank.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertTrue(passwordIsNotBlank.isValid("foo123"));
	}
}
