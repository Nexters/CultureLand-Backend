package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.dto.CultureIdImgDto;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.CultureRawData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CultureRepository extends JpaRepository<Culture, Long>{

}
