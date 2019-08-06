package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.Culture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CultureRepo extends JpaRepository<Culture, Long>{

    //카테고리에 맞는 문화생활 조회
    @Query(value = "select c from Culture c where c.cultureName = :cultureName")
    Page<Culture> findByCultureName(String cultureName, Pageable pageable);

}
