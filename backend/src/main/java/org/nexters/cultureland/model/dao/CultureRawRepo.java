package org.nexters.cultureland.model.dao;

import org.nexters.cultureland.model.vo.Culture;
import org.nexters.cultureland.model.vo.CultureRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CultureRawRepo extends JpaRepository<CultureRawData, Long>{

    //상세정보 조회
    //public CultureRawData findByOne(Long id);

    //상세정보 조회
/*    @Query(value="select r from CultureRawData r where r.id = ?1")
    public Object findByCultureRawDatasId(Long id);*/

    public List<Object> findByTitleIgnoreCaseContaining(String title);
}
