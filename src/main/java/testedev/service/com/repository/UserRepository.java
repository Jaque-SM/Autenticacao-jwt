package testedev.service.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import testedev.service.com.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

		Optional<User> findByEmail(String email);
	
}
