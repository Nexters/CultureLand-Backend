package org.nexters.cultureland.service;

import org.nexters.cultureland.dto.CultureDto;
import org.nexters.cultureland.model.Culture;
import org.nexters.cultureland.model.CultureRawData;

import java.util.List;

public interface CultureService {
    //최근순으로 문화생활 전체 목록 조회(id, img)
    public List<CultureDto> getList();

    //카테고리에 맞는 문화생활 조회
    public List<CultureDto> getByCategory(String cultureName);

    //상세정보 조회
    public CultureRawData getByCultureId(Long id);

    //검색어에 맞는 문화생활 조회
    public List<Culture> getBySearch(String query);

}
