package com.nexters.cultureland.repo;

import com.nexters.cultureland.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepsitory extends JpaRepository<Culture, Long> {
    Culture findByCultureName(String cultureName);
}
