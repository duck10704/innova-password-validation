package com.innovasolutions.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.rule.PasswordIsBlank;

public class PasswordIsBlankTest {

	private PasswordIsBlank passwordIsBlank;

	@Before
	public void before() {
		passwordIsBlank = new PasswordIsBlank();
	}

	@After
	public void after() {
		passwordIsBlank = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordIsBlank.isValid(null));
		assertFalse(passwordIsBlank.isValid(""));
		assertFalse(passwordIsBlank.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertTrue(passwordIsBlank.isValid("foo123"));
	}
}
