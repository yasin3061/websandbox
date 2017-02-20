package org.websandbox.learning.layout.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByName(String name);

	public Optional<User> findById(Long id);

	public List<User> findAll();

}
