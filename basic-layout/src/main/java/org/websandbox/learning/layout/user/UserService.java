package org.websandbox.learning.layout.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

	public Optional<User> getUserById(Long id);

	public Optional<User> getUserByName(String name);

	public List<User> getAllUsers();

	public boolean exists(String email);

	public User save(User user);
}
