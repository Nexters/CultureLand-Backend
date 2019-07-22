package org.nexters.cultureland.repo;

import org.nexters.cultureland.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByuserId(long userId);
    Optional<User> findByuserId(long userId);
}
