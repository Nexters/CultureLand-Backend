package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.CultureRawData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CultureRawRepository extends JpaRepository<CultureRawData, Long>, JpaSpecificationExecutor<CultureRawData> {

    //select * from Culutre_rawdata where title like '%해당값%'
    public List<CultureRawData> findByTitleIgnoreCaseContaining(String title);

    public Page<CultureRawData> findAll(Pageable page);

    public Page<CultureRawData> findAll(Specification<CultureRawData> spec, Pageable page);
}
