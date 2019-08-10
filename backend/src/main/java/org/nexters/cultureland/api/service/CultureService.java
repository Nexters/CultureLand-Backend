package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.CultureDetailDto;
import org.nexters.cultureland.api.dto.CultureResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CultureService {

    //문화생활 전체 목록 조회(id, img)
    CultureResponse getAll(Pageable page);

    //카테고리에 맞는 문화생활 조회
    CultureResponse getByCategoryPage(String category,Pageable page);

    //상세정보 조회
    CultureDetailDto getByCultureId(Long id);

    //검색어query에 맞는 문화생활 조회
    List getBySearch(String query);

    //검색어에 맞는 title 조회
    List getTitleBySearch(String query);

}
