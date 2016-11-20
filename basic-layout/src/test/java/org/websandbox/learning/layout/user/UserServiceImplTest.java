package org.websandbox.learning.layout.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public UserRepository userRepository() {
			return Mockito.mock(UserRepository.class);
		}
	}

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {
		User john = new User("john");
		john.setId(11L);

		Optional<User> user = Optional.of(john);

		User bob = new User("bob");
		User alex = new User("alex");

		List<User> allUsers = Arrays.asList(john, bob, alex);

		Mockito.when(userRepository.findByName(john.getName())).thenReturn(user);
		Mockito.when(userRepository.findByName("wrong_name")).thenReturn(Optional.empty());
		Mockito.when(userRepository.findById(john.getId())).thenReturn(user);
		Mockito.when(userRepository.findAll()).thenReturn(allUsers);
		Mockito.when(userRepository.findById(-99L)).thenReturn(Optional.empty());
	}

	@Test
	public void givenValidNameUserShouldBeFound() {
		Optional<User> userFromDb = userService.getUserByName("john");
		assertThat(userFromDb.get().getName()).isEqualTo("john");

		verifyFindByNameIsCalledOnce("john");
	}

	@Test(expected = NoSuchElementException.class)
	public void givenInValidNameUserShouldNotBeFound() {
		Optional<User> userFromDb = userService.getUserByName("wrong_name");
		userFromDb.get();

		verifyFindByNameIsCalledOnce("wrong_name");
	}

	@Test
	public void givenValidNameUserShouldExist() {
		boolean doesUserExist = userService.exists("john");
		assertThat(doesUserExist).isEqualTo(true);

		verifyFindByNameIsCalledOnce("john");
	}

	@Test
	public void givenNonExistingNameUserShouldNotExist() {
		boolean doesUserExist = userService.exists("some_name");
		assertThat(doesUserExist).isEqualTo(false);

		verifyFindByNameIsCalledOnce("some_name");
	}

	@Test
	public void givenValidIdUserShouldBeFound() {
		Optional<User> userFromDb = userService.getUserById(11L);
		assertThat(userFromDb.get().getName()).isEqualTo("john");

		verifyFindByIdIsCalledOnce();
	}

	@Test(expected = NoSuchElementException.class)
	public void givenInValidIdUserShouldNotBeFound() {
		Optional<User> userFromDb = userService.getUserById(-99L);
		verifyFindByIdIsCalledOnce();
		userFromDb.get();
	}

	@Test
	public void findAllUsers() {
		User alex = new User("alex");
		User john = new User("john");
		User bob = new User("bob");

		List<User> allUsers = userService.getAllUsers();
		verifyFindAllUsersIsCalledOnce();
		assertThat(allUsers).hasSize(3).extracting(User::getName).contains(alex.getName(), john.getName(),
				bob.getName());

	}

	private void verifyFindByNameIsCalledOnce(String name) {
		Mockito.verify(userRepository, VerificationModeFactory.times(1)).findByName(name);
		Mockito.reset(userRepository);
	}

	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
		Mockito.reset(userRepository);
	}

	private void verifyFindAllUsersIsCalledOnce() {
		Mockito.verify(userRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(userRepository);
	}
}
