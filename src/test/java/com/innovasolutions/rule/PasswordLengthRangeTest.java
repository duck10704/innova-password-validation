package com.innovasolutions.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.innovasolutions.validation.rule.PasswordLengthInRange;

public class PasswordLengthRangeTest {

	private PasswordLengthInRange passwordLengthInRange;

	@Before
	public void before() {
		passwordLengthInRange = new PasswordLengthInRange(5, 12);
	}

	@After
	public void after() {
		passwordLengthInRange = null;
	}

	@Test
	public void blankTests() {
		assertFalse(passwordLengthInRange.isValid(null));
		assertFalse(passwordLengthInRange.isValid(""));
		assertFalse(passwordLengthInRange.isValid("   "));
	}

	@Test
	public void notBlankTests() {
		assertFalse(passwordLengthInRange.isValid("foo"));
		assertFalse(passwordLengthInRange.isValid("foo123foo123foo123"));

		assertTrue(passwordLengthInRange.isValid("foo12"));
		assertTrue(passwordLengthInRange.isValid("foo123"));
		assertTrue(passwordLengthInRange.isValid("foo123foo123"));
	}

	@Test
	public void ramdomStringTests() {
		final String random = RandomStringUtils.randomAlphabetic(5, 12);
		final Integer length = random.length();
		for (int i = 0; i < 100; i++) {
			if (length < 5 || length > 12) {
				assertFalse(passwordLengthInRange.isValid(random));
			} else {
				assertTrue(passwordLengthInRange.isValid(random));
			}
		}
	}
}
