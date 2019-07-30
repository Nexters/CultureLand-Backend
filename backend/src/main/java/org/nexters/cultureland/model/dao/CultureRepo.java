package org.nexters.cultureland.model.dao;

import org.nexters.cultureland.model.vo.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CultureRepo extends JpaRepository<Culture, Long>{
    //카테고리에 맞는 문화생활 조회
    List<Culture> findByCultureName(String cultureName);

    //문화생활 전체 목록 조회(id, img)
    @Query(value="select id, imageUrl from CultureRawData")
    public List<Object> findList();
}
