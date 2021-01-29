package com.innovasolutions.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.innovasoluation.model.Password;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PasswordValidationTest extends AbstractTest {

	@Test
	public void testPasswordIsBlank() throws Exception {
		final String json = toJsonString(new Password());
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordTooShort() throws Exception {
		final String json = toJsonString(new Password("foo1"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordTooLong() throws Exception {
		final String json = toJsonString(new Password("test123password"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithUpperCase() throws Exception {
		final String json = toJsonString(new Password("Foo123"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithoutLowerCase() throws Exception {
		final String json = toJsonString(new Password("FOO123"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordWithoutNumerical() throws Exception {
		final String json = toJsonString(new Password("FOOTEST"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPasswordRepeating() throws Exception {
		final String json = toJsonString(new Password("foo123foo123"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testInValidPassword() throws Exception {
		final String json = toJsonString(new Password("foo123@test"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_ACCEPTABLE.name()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testValidPassword() throws Exception {
		final String json = toJsonString(new Password("foo123test"));
		mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URL).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.name()))
				.andDo(MockMvcResultHandlers.print());
	}
}
