package org.websandbox.learning.layout.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Test
	public void searchExistingUserByEmail() {
		User user = new User("test");
		entityManager.persistAndFlush(user);

		Optional<User> fromDb = userRepository.findByName(user.getName());
		assertThat(fromDb.get().getName()).isEqualTo(user.getName());
	}

	@Test(expected = NoSuchElementException.class)
	public void searchNonExistingUserByEmail() {
		Optional<User> fromDb = userRepository.findByName("doesNotExist");
		fromDb.get();
	}

	@Test
	public void searchExistingUserById() {
		User user = new User("test");
		entityManager.persistAndFlush(user);

		Optional<User> fromDb = userRepository.findById(user.getId());
		assertThat(fromDb.get().getName()).isEqualTo(user.getName());
	}

	@Test(expected = NoSuchElementException.class)
	public void searchNonExistingUserById() {
		Optional<User> fromDb = userRepository.findById(-11L);
		fromDb.get();
	}

	@Test
	public void findAllUsers() {
		User alex = new User("alex");
		User ron = new User("ron");
		User bob = new User("bob");

		entityManager.persist(alex);
		entityManager.persist(bob);
		entityManager.persist(ron);
		entityManager.flush();

		List<User> allUsers = userRepository.findAll();

		assertThat(allUsers).hasSize(3).extracting(User::getName).containsOnly(alex.getName(), ron.getName(),
				bob.getName());
	}

}
