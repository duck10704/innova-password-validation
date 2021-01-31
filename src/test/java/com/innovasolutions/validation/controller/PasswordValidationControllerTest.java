package com.innovasolutions.validation.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.innovasolutions.validation.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PasswordValidationControllerTest extends AbstractTest {

	@Test
	public void testPasswordIsNull() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testPasswordIsEmpty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String())).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testPasswordIsNotTrim() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("   "))).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testPasswordTooShort() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("foo"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordTooLong() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("foo123foo123foo123"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordWithSpecialCharacter() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("foo123@test"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordWithUpperCase() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("Foo123"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordWithoutLowerCase() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("FOO123"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordWithoutNumerical() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("FOOtest"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testPasswordRepeating() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("foo123foo123"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	@Test
	public void testValidPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new String("foo123test"))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.OK.value()))
				.andDo(MockMvcResultHandlers.print());
	}
}
