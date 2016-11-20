package org.websandbox.learning.layout.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.websandbox.learning.springdata.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	UserService service;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createUser() throws Exception {
		User alex = new User("alex");
		given(service.save(Mockito.anyObject())).willReturn(alex);

		mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("alex")));
		verify(service, VerificationModeFactory.times(1)).save(Mockito.anyObject());
		reset(service);
	}

	@Test
	public void getAllUsers() throws Exception {
		User alex = new User("alex");
		User john = new User("john");
		User bob = new User("bob");

		List<User> allUsers = Arrays.asList(alex, john, bob);

		given(service.getAllUsers()).willReturn(allUsers);

		mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].name", is(alex.getName())))
				.andExpect(jsonPath("$[1].name", is(john.getName())))
				.andExpect(jsonPath("$[2].name", is(bob.getName())));
		verify(service, VerificationModeFactory.times(1)).getAllUsers();
		reset(service);
	}

}