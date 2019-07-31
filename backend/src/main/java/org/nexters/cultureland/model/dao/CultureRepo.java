package org.nexters.cultureland.model.dao;

import org.nexters.cultureland.model.vo.Culture;
import org.nexters.cultureland.model.vo.CultureRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CultureRepo extends JpaRepository<Culture, Long>{
    //최근순으로 문화생활 전체 목록 조회(id, img)
    @Query(value="select id, imageUrl from CultureRawData order by startDate desc")
    public List<Object> findList();

    //카테고리에 맞는 문화생활 조회
    //select * from culture where culture_name = "concnert";
    public List<Culture> findByCultureName(String cultureName);

}
