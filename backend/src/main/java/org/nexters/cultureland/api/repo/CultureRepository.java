package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CultureRepository extends JpaRepository<Culture, Long> {
    Optional<Culture> findByCultureName(String cultureName);
}
