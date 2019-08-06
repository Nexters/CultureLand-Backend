package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.CultureDetailDto;
import org.nexters.cultureland.api.dto.CultureIdImgDto;
import org.nexters.cultureland.api.dto.CultureTitleDto;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.CultureRawData;

import java.util.List;

public interface CultureService {
    //최근순으로 문화생활 전체 목록 조회(id, img)
    public List<CultureIdImgDto> getList();

    //카테고리에 맞는 문화생활 조회
    public List<CultureIdImgDto> getByCategory(String cultureName);

    //상세정보 조회
    public CultureDetailDto getByCultureId(Long id);

    //검색어에 맞는 문화생활 조회
    public List<CultureIdImgDto> getBySearch(String query);

    //검색어에 맞는 title 조회
    public List<CultureTitleDto> getTitleBySearch(String query);

}
