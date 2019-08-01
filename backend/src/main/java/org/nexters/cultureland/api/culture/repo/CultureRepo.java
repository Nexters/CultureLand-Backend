package org.nexters.cultureland.api.culture.repo;

import org.nexters.cultureland.api.culture.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CultureRepo extends JpaRepository<Culture, Long>{

    //카테고리에 맞는 문화생활 조회
    public List<Culture> findByCultureName(String cultureName);

}
