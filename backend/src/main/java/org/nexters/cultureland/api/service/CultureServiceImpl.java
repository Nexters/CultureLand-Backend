package org.nexters.cultureland.api.service;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.dto.*;
import org.nexters.cultureland.api.model.CultureRawData;
import org.nexters.cultureland.api.repo.CultureRawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.nexters.cultureland.api.specification.CultureSpec.cultureName;
import static org.springframework.data.jpa.domain.Specification.where;

@Service("CultureService")
@Transactional
public class CultureServiceImpl implements CultureService {

    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않습니다.";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CultureRawRepository cultureRawRepo;

    // 페이지로 전체 목록 조회
    public CultureResponse getAll(Pageable page){

        Page<CultureIdImgDto> pages = cultureRawRepo.findAll(page).map(CultureIdImgDto::new);
        CultureResponse cultureResponse = CultureResponse.builder()
                .contents(pages.getContent())
                .count(pages.getSize())
                .nextPage(pages.hasNext())
                .totalCount(pages.getTotalElements())
                .build();
        return cultureResponse;
    }

    //카테고리별로 id, img 값 반환
    public CultureResponse getByCategoryPage(String category,Pageable page) {

        Page<CultureIdImgDto> culturePage = cultureRawRepo.findAll(where(cultureName(category)),page).map(CultureIdImgDto::new);

        CultureResponse cultureResponse = CultureResponse.builder()
                .contents(culturePage.getContent())
                .count(culturePage.getSize())
                .nextPage(culturePage.hasNext())
                .totalCount(culturePage.getTotalElements())
                .build();
        return cultureResponse;
    }

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
    }
        List<CultureRawData> cultureRawData = c
        culture = cultureRepo.findByCultureName(category,page).map(CultureIdImgDto::new);
        return culture.getCultureRawDatas().stream()
                .map(data -> modelMapper.map(data, CultureIdImgDto.class))
                .collect(Collectors.toList()); */
}
