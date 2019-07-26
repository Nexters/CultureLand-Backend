package org.nexters.cultureland.model.dao;

import org.nexters.cultureland.model.vo.Culture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CultureRepo extends JpaRepository<Culture, Long>{
    List<Culture> findAll();
    List<Culture> findByCultureName(String cultureName);
}
