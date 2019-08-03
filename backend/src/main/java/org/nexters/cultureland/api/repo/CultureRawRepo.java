package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.dto.CultureDto;
import org.nexters.cultureland.api.model.CultureRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CultureRawRepo extends JpaRepository<CultureRawData, Long>{

    @Query(value="select new org.nexters.cultureland.api.culture.dto.CultureDto(c.id, c.imageUrl) from CultureRawData c order by startDate desc")
    public List<CultureDto> findList();
    public List<CultureRawData> findByTitleIgnoreCaseContaining(String title);
}
