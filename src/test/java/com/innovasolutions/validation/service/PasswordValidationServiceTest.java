package com.innovasolutions.validation.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.innovasolutions.validation.AbstractTest;
import com.innovasolutions.validation.model.ValidationException;
import com.innovasolutions.validation.model.ValidationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PasswordValidationServiceTest extends AbstractTest {

	@Test(expected = ValidationException.class)
	public void testIsNull() throws Exception {
		passwordValidationService.getPasswordValidationResponse(null);
	}

	@Test(expected = ValidationException.class)
	public void testIsEmtpy() throws Exception {
		passwordValidationService.getPasswordValidationResponse(StringUtils.EMPTY);
	}

	@Test(expected = ValidationException.class)
	public void testIsNotTrim() throws Exception {
		passwordValidationService.getPasswordValidationResponse("   ");
	}

	@Test
	public void testTooShort() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("foo1");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testTooLong() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("foo123foo123foo123");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testContainSpecialCharacter() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("foo123@test");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testContainUpperCaseCharacter() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("Foo123");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testWithoutLowerCase() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("FOO123");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testWithoutNumerical() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("FOOtest");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testRepeatingString() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("foo123foo123");
		assertFalse(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertTrue(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testValid() {
		final ValidationResponse r = passwordValidationService.getPasswordValidationResponse("foo123test");
		assertTrue(r.getStatusCode().equals(HttpStatus.OK.value()));
		assertFalse(r.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE.value()));
	}
}
