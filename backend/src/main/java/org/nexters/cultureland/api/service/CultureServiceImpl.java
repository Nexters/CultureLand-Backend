package org.nexters.cultureland.service;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.dto.CultureDto;
import org.nexters.cultureland.model.Culture;
import org.nexters.cultureland.model.CultureRawData;
import org.nexters.cultureland.repo.CultureRawRepo;
import org.nexters.cultureland.repo.CultureRepo;
import org.nexters.cultureland.common.excepion.NotFoundDiaryException;
import org.springframework.beans.factory.annotation.Autowired;
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
        return cultureRawRepo.findList();
    }


////     bug : 실제 카테고리에 맞는 데이터가 나타나는게 아니라 카테고리만 출력
//    public List getByCategory(String category) {
//        return cultureRepo.findByCultureName(category).stream()
//                .map(culture -> modelMapper.map(culture, CultureDto.class))
//                .collect(Collectors.toList());
////        return cultureRepo.findByCultureName(category);
//    }

    public List getByCategory(String category) {
        Culture culture = cultureRepo.findByCultureName(category).orElseThrow(IllegalArgumentException::new);
        return culture.getCultureRawDatas().stream()
            .map(data -> modelMapper.map(data, CultureDto.class))
            .collect(Collectors.toList());
    }


    public CultureRawData getByCultureId(Long id) {
        return cultureRawRepo.findById(id)
                .orElseThrow(() -> new NotFoundDiaryException(NOT_FOUND_ERROR_MESSAGE));
    }


    public List getBySearch(String query) {
        return cultureRawRepo.findByTitleIgnoreCaseContaining(query);
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
