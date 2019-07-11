package com.nexters.cultureland.repo;

import com.nexters.cultureland.model.CultureRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepository extends JpaRepository<CultureRawData, Long> {
}
