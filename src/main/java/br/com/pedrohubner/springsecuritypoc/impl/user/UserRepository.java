package br.com.pedrohubner.springsecuritypoc.impl.user;

import br.com.pedrohubner.springsecuritypoc.impl.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
