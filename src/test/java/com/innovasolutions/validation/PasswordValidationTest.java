package com.innovasolutions.validation;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PasswordValidationTest extends AbstractTest {

	@Test
	public void testPasswordTooShort() throws Exception {
		final String json = toJsonString("foo1");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordTooLong() throws Exception {
		final String json = toJsonString("test123password");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithUpperCase() throws Exception {
		final String json = toJsonString("Foo123");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithoutLowerCase() throws Exception {
		final String json = toJsonString("FOO123");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithoutNumerical() throws Exception {
		final String json = toJsonString("FOOTEST");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordRepeating() throws Exception {
		final String json = toJsonString("foo123foo123");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testInValidPassword() throws Exception {
		final String json = toJsonString("foo123@test");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testValidPassword() throws Exception {
		final String json = toJsonString("foo123test");
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.name()))
				.andDo(MockMvcResultHandlers.print());
	}
}
