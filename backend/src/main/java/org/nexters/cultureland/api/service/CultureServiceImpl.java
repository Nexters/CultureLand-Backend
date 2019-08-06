package org.nexters.cultureland.api.service;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.dto.CultureDetailDto;
import org.nexters.cultureland.api.dto.CultureIdImgDto;
import org.nexters.cultureland.api.dto.CultureResponse;
import org.nexters.cultureland.api.dto.CultureTitleDto;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.CultureRawData;
import org.nexters.cultureland.api.repo.CultureRawRepo;
import org.nexters.cultureland.api.repo.CultureRepo;
import org.nexters.cultureland.api.exception.NotFoundDiaryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("CultureService")
@Transactional
public class CultureServiceImpl implements CultureService {

    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않습니다.";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CultureRepo cultureRepo;

    @Autowired
    private CultureRawRepo cultureRawRepo;

    //전체 목록 조회
    public List getList() {
        return cultureRawRepo.findAll().stream()
                .map(data -> modelMapper.map(data, CultureIdImgDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CultureIdImgDto> getByCategory(String cultureName) {
        return null;
    }

    // 페이지로 전체 목록 조회
    public CultureResponse getAll(Pageable page){
        //Pageable pageable = PageRequest.of()
        Page<CultureIdImgDto> pages = cultureRawRepo.findAll(page).map(CultureIdImgDto::new);
        CultureResponse cultureResponse = CultureResponse.builder()
                .contents(pages.getContent())
                .count(pages.getSize())
                .nextPage(pages.hasNext())
                .totalCount(pages.getTotalElements())
                .build();
        return cultureResponse;
    }
    // 페이지로 인기순 목록 조회

    //카테고리별로 id, img 값 반환
    public List getByCategoryPage(String category,Pageable page) {
//        select * from culture where culture.name = query

//        select * from rawculture rc where rc.id = (select id from culture c where c.name = query)
        // 1. 카테고리가 "concert " culture 값을 받아온다.
        // 2. culture의 CultureRawData를 List로 받아옴
        Page<Culture> culture = cultureRepo.findByCultureName(category,page);
//        List<CultureRawData> cultureRawData = c
//        culture = cultureRepo.findByCultureName(category,page).map(CultureIdImgDto::new);
//        return culture.getCultureRawDatas().stream()
//                .map(data -> modelMapper.map(data, CultureIdImgDto.class))
//                .collect(Collectors.toList());
        return null;
    }

/*    //카테고리별로 id, img 값 반환
    public List getByCategory(String category) {
        Culture culture = cultureRepo.findByCultureName(category).orElseThrow(IllegalArgumentException::new);
        return culture.getCultureRawDatas().stream()
            .map(data -> modelMapper.map(data, CultureIdImgDto.class))
            .collect(Collectors.toList());
    }*/


    //상세페이지
    public CultureDetailDto getByCultureId(Long id) {
        CultureRawData c = cultureRawRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        CultureDetailDto dto = new CultureDetailDto(c.getId(),c.getImageUrl(),c.getTitle(),c.getPlace(),c.getStartDate(),c.getEndDate(),c.getCulture().getCultureName());
        return dto;
    }


    //검색어query에 맞는 문화생활 조회
    public List getBySearch(String query) {
        return cultureRawRepo.findByTitleIgnoreCaseContaining(query).stream()
                .map(data -> modelMapper.map(data, CultureIdImgDto.class))
                .collect(Collectors.toList());
    }

    // 검색어query에 맞는 title 조회
    public List getTitleBySearch(String query) {
        return cultureRawRepo.findByTitleIgnoreCaseContaining(query).stream()
                .map(data -> modelMapper.map(data, CultureTitleDto.class))
                .collect(Collectors.toList());
    }
/*  //테스트 코드
    public List<CultureRawData> getAll(){
        List<CultureRawData> test  = cultureRawRepo.findAll();
        for(CultureRawData c : test) {
           c.setCulture(c.getCulture());
        }
        return test;
    }*/
}
