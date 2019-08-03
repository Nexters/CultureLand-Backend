package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CultureRepo extends JpaRepository<Culture, Long>{

    //카테고리에 맞는 문화생활 조회
    public Optional<Culture> findByCultureName(String cultureName);

}