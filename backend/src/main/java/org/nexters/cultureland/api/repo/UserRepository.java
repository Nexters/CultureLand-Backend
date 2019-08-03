package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByuserId(long userId);
    Optional<User> findByuserId(long userId);
    void deleteByuserId(long userId);
}
