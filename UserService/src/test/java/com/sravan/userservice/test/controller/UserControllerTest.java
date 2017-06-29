package com.sravan.userservice.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sravan.userservice.controller.UserController;
import com.sravan.userservice.model.User;
import com.sravan.userservice.service.UserService;
import com.sravan.userservice.test.utils.TestUtils;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	private final String URL = "/userService/user";
	
	
	@Test
	public void testAddUser() throws Exception {

		// prepare data and mock's behaviour
		User userStub = new User(1, "firstName", "lastName", "password", 12000,"example@example.com","address","userId");
		when(userService.saveUser(any(User.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(userStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(userService).saveUser(any(User.class));

		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
		assertNotNull(resultUser);
		assertEquals(1l, resultUser.getId());

	}
	
	@Test
	public void testGetUser() throws Exception {

		// prepare data and mock's behaviour
		User userStub = new User(1l, "firstName", "lastName", "password", 12000,"example@example.com","address","userId");
		when(userService.getUserInfo(any(Long.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).getUserInfo(any(Long.class));

		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
		assertNotNull(resultUser);
		assertEquals(1l, resultUser.getId());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		// prepare data and mock's behaviour
		// here the stub is the updated employee object with ID equal to ID of
		// employee need to be updated
		User userStub = new User(1l, "firstName", "lastName", "passwordchanged", 12000,"example@example.com","address","userId");
		when(userService.getUserInfo(any(Long.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(userStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).saveUser(any(User.class));

	}

	@Test
	public void testDeleteUser() throws Exception {
		// prepare data and mock's behaviour
		User userStub = new User(1l);
		when(userService.getUserInfo(any(Long.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", new Long(1))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

		// verify that service method was called once
		verify(userService).deleteUser(any(Long.class));

	}

	


	
}
