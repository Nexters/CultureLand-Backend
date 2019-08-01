package org.nexters.cultureland.api.user.repo;

import org.nexters.cultureland.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByuserId(long userId);
    User findByuserId(long userId);
    void deleteByuserId(long userId);
}
