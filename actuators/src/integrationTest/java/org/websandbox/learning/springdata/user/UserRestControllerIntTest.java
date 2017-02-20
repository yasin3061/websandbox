package org.websandbox.learning.springdata.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.websandbox.learning.layout.BasicLayoutApplication;
import org.websandbox.learning.layout.user.User;
import org.websandbox.learning.layout.user.UserRepository;
import org.websandbox.learning.springdata.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BasicLayoutApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserRestControllerIntTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	UserRepository repository;

	@After
	public void resetDb() {
		repository.deleteAll();
	}

	@Test
	public void givenValidInputItShouldCreateUser() throws IOException, Exception {
		User bob = new User("bob");
		mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)));

		List<User> usersFound = repository.findAll();
		assertThat(usersFound).extracting(User::getName).containsOnly("bob");
	}

	@Test
	public void shouldListAllUsers() throws Exception {

		createUser("bob");
		createUser("alex");

		mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2)))).andExpect(jsonPath("$[0].name", is("bob")))
				.andExpect(jsonPath("$[1].name", is("alex")));
	}

	private void createUser(String name) {
		User user = new User(name);
		repository.saveAndFlush(user);
	}

}
