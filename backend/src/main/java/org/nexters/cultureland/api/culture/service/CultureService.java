package org.nexters.cultureland.api.culture.service;

import org.nexters.cultureland.api.culture.dto.CultureDto;
import org.nexters.cultureland.api.culture.model.Culture;
import org.nexters.cultureland.api.culture.model.CultureRawData;

import java.util.List;
import java.util.Optional;

public interface CultureService {
    //최근순으로 문화생활 전체 목록 조회(id, img)
    public List<CultureDto> getList();

    //카테고리에 맞는 문화생활 조회
    public List<CultureDto> getByCategory(String cultureName);

    //상세정보 조회
    public Optional<CultureRawData> getByCultureId(Long id);

    //검색어에 맞는 문화생활 조회
    public List<Object> getBySearch(String query);

}
