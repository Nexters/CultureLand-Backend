package org.nexters.cultureland.api.culture.service;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.culture.repo.CultureRawRepo;
import org.nexters.cultureland.api.culture.repo.CultureRepo;
import org.nexters.cultureland.api.culture.model.CultureRawData;
import org.nexters.cultureland.common.excepion.NotFoundDiaryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("CultureService")
@Transactional
public class CultureServiceImpl implements CultureService{

    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않습니다.";

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private CultureRepo cultureRepo;

    @Autowired
    private CultureRawRepo cultureRawRepo;

    //전체 목록 조회
    public List getList() {
        return cultureRawRepo.findList();
    }

    public List getSortList(Sort sort) {
        return cultureRawRepo.findAll(sort);
    }


    public List getByCategory(String category) {
        //return cultureRepo.findByCultureName(category).stream().map(culture -> modelMapper.map(culture,CultureDto.class)).collect(Collectors.toList());
        return cultureRepo.findByCultureName(category);
    }


    public CultureRawData getByCultureId(Long id) {
        return cultureRawRepo.findById(id)
                .orElseThrow(() -> new NotFoundDiaryException(NOT_FOUND_ERROR_MESSAGE));
    }


    public List getBySearch(String query) {
        return cultureRawRepo.findByTitleIgnoreCaseContaining(query);
    }

  //테스트 코드
    public Page<CultureRawData> getAll(Pageable page){
        return cultureRawRepo.findAll(page);
    }
}
