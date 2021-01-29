package com.innovasolutions.validation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {

	protected final static String PASSWORD_VALIDATION_URL = "/password/validation";

	@Autowired
	protected MockMvc mockMvc;

	@BeforeAll
	protected void setUp() {
	}

	@AfterAll
	protected void tearDown() {
	}

	protected String toJsonString(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
