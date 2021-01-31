package com.innovasolutions.validation.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.AbstractTest;
import com.innovasolutions.validation.rule.PasswordContainLowercaseCharacter;

public class PasswordLowercaseTest extends AbstractTest {

	private PasswordContainLowercaseCharacter passwordContainLowercaseCharacter;

	@Before
	public void before() {
		passwordContainLowercaseCharacter = new PasswordContainLowercaseCharacter();
	}

	@After
	public void after() {
		passwordContainLowercaseCharacter = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordContainLowercaseCharacter.isValid(null));
		assertFalse(passwordContainLowercaseCharacter.isValid(""));
		assertFalse(passwordContainLowercaseCharacter.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertFalse(passwordContainLowercaseCharacter.isValid("FOO"));
		assertFalse(passwordContainLowercaseCharacter.isValid("FOO123"));
		assertFalse(passwordContainLowercaseCharacter.isValid("123456"));

		assertTrue(passwordContainLowercaseCharacter.isValid("foo123"));
		assertTrue(passwordContainLowercaseCharacter.isValid("foo123foo123"));
		assertTrue(passwordContainLowercaseCharacter.isValid("FOO123foo123"));
	}

	@Test
	public void randomAllLowercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomLowercaseString(10);
			assertTrue(passwordContainLowercaseCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllUppercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomUppercaseString(10);
			assertFalse(passwordContainLowercaseCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllNumericalTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomNumericalString(10);
			assertFalse(passwordContainLowercaseCharacter.isValid(random));
		}
	}
}
