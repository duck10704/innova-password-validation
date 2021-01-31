package com.innovasolutions.validation.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordSepcialTest {

	private PasswordContainSpecialCharacter passwordContainSpecialCharacter;

	@Before
	public void before() {
		passwordContainSpecialCharacter = new PasswordContainSpecialCharacter();
	}

	@After
	public void after() {
		passwordContainSpecialCharacter = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordContainSpecialCharacter.isValid(null));
		assertFalse(passwordContainSpecialCharacter.isValid(""));
		assertFalse(passwordContainSpecialCharacter.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertTrue(passwordContainSpecialCharacter.isValid("@"));
		assertTrue(passwordContainSpecialCharacter.isValid("foo#"));
		assertTrue(passwordContainSpecialCharacter.isValid("~foo"));
		assertTrue(passwordContainSpecialCharacter.isValid("~#@="));

		assertFalse(passwordContainSpecialCharacter.isValid("FOO"));
		assertFalse(passwordContainSpecialCharacter.isValid("foo123"));
		assertFalse(passwordContainSpecialCharacter.isValid("123456"));
	}
}
