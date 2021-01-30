package com.innovasolutions.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.AbstractTest;
import com.innovasolutions.validation.rule.PasswordContainRepeatingString;

public class PasswordRepeatingTest extends AbstractTest {

	private PasswordContainRepeatingString passwordContainRepeatingString;

	@Before
	public void before() {
		passwordContainRepeatingString = new PasswordContainRepeatingString();
	}

	@After
	public void after() {
		passwordContainRepeatingString = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordContainRepeatingString.isValid(null));
		assertFalse(passwordContainRepeatingString.isValid(""));
		assertFalse(passwordContainRepeatingString.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertFalse(passwordContainRepeatingString.isValid("a"));
		assertFalse(passwordContainRepeatingString.isValid("1"));
		assertFalse(passwordContainRepeatingString.isValid("foo123"));
		assertFalse(passwordContainRepeatingString.isValid("FOO123321OOF"));
		assertFalse(passwordContainRepeatingString.isValid("foo123foo"));

		assertTrue(passwordContainRepeatingString.isValid("11"));
		assertTrue(passwordContainRepeatingString.isValid("AA"));
		assertTrue(passwordContainRepeatingString.isValid("BBB"));
		assertTrue(passwordContainRepeatingString.isValid("ABCABC"));
		assertTrue(passwordContainRepeatingString.isValid("123123"));
		assertTrue(passwordContainRepeatingString.isValid("foofoo"));
		assertTrue(passwordContainRepeatingString.isValid("foo123foo123"));
	}
}
