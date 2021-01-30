package com.innovasolutions.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.AbstractTest;
import com.innovasolutions.validation.rule.PasswordContainUppercaseCharacter;

public class PasswordUppercaseTest extends AbstractTest {

	private PasswordContainUppercaseCharacter passwordContainUppercaseCharacter;

	@Before
	public void before() {
		passwordContainUppercaseCharacter = new PasswordContainUppercaseCharacter();
	}

	@After
	public void after() {
		passwordContainUppercaseCharacter = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordContainUppercaseCharacter.isValid(null));
		assertFalse(passwordContainUppercaseCharacter.isValid(""));
		assertFalse(passwordContainUppercaseCharacter.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertFalse(passwordContainUppercaseCharacter.isValid("foo"));
		assertFalse(passwordContainUppercaseCharacter.isValid("foo123"));
		assertFalse(passwordContainUppercaseCharacter.isValid("123456"));

		assertTrue(passwordContainUppercaseCharacter.isValid("FOO"));
		assertTrue(passwordContainUppercaseCharacter.isValid("FOO123"));
		assertTrue(passwordContainUppercaseCharacter.isValid("FOO123foo123"));
	}

	@Test
	public void randomAllLowercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomLowercaseString(10);
			assertFalse(passwordContainUppercaseCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllUppercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomUppercaseString(10);
			assertTrue(passwordContainUppercaseCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllNumericalTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomNumericalString(10);
			assertFalse(passwordContainUppercaseCharacter.isValid(random));
		}
	}
}
