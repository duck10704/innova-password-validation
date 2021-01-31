package com.innovasolutions.validation.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.AbstractTest;
import com.innovasolutions.validation.rule.PasswordContainNumericalCharacter;

public class PasswordNumericalTest extends AbstractTest {

	private PasswordContainNumericalCharacter passwordContainNumericalCharacter;

	@Before
	public void before() {
		passwordContainNumericalCharacter = new PasswordContainNumericalCharacter();
	}

	@After
	public void after() {
		passwordContainNumericalCharacter = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordContainNumericalCharacter.isValid(null));
		assertFalse(passwordContainNumericalCharacter.isValid(""));
		assertFalse(passwordContainNumericalCharacter.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertFalse(passwordContainNumericalCharacter.isValid("FOO"));
		assertFalse(passwordContainNumericalCharacter.isValid("foo"));

		assertTrue(passwordContainNumericalCharacter.isValid("123456"));
		assertTrue(passwordContainNumericalCharacter.isValid("FOO123"));
		assertTrue(passwordContainNumericalCharacter.isValid("foo123"));
	}

	@Test
	public void randomAllLowercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomLowercaseString(10);
			assertFalse(passwordContainNumericalCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllUppercaseTests() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomUppercaseString(10);
			assertFalse(passwordContainNumericalCharacter.isValid(random));
		}
	}

	@Test
	public void randomAllNumerical() {
		for (int i = 0; i < 10; i++) {
			final String random = genRandomNumericalString(10);
			assertTrue(passwordContainNumericalCharacter.isValid(random));
		}
	}
}
