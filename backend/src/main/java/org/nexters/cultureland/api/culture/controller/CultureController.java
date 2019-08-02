package org.nexters.cultureland.api.culture.controller;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.culture.dto.CultureDto;
import org.nexters.cultureland.api.culture.model.Culture;
import org.nexters.cultureland.api.culture.model.CultureRawData;
import org.nexters.cultureland.api.culture.service.CultureService;
import org.nexters.cultureland.api.culture.service.CultureServiceImpl;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "/cultureInfos")
public class CultureController {

    @Autowired
    private CultureServiceImpl cultureService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseMessage readCultures(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                        @RequestParam(value = "find", required = false, defaultValue = "") String find) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();

        //전체 목록 조회
        if(category.equals("") && find.equals("")) {
            List<CultureDto> cultures = cultureService.getList();
            responseMessage.setMessage(cultures);

        }
        //카테고리에 맞는 문화생활 조회
        else if(category.length() != 0 && find.equals("")) {
            List<CultureDto> cultures = cultureService.getByCategory(category);
            responseMessage.setMessage(cultures);
        }
        //검색어 query에 맞는 문화생활 조회
        else {
            List<Object> cultureRawDatas = cultureService.getBySearch(find);
            responseMessage.setMessage(cultureRawDatas);
        }

        return responseMessage;
    }

    //인기순으로 전체조회
    @GetMapping("/{pNo}/{size}/{sort}")
    public ResponseMessage getListBySort(@PathVariable("pNo") int pNo, @PathVariable("size") int size, @PathVariable("sort") String sort) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        PageRequest request = new PageRequest(pNo-1,size, Sort.Direction.ASC,sort);
        responseMessage.setMessage(cultureService.getAll(request));

        return responseMessage;
    }

    //문화생활 상세조회
    @GetMapping("/{cultureInfoId}")
    public ResponseMessage readDetailById(@PathVariable("cultureInfoId") Long id) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        CultureRawData cultureRawDatas = cultureService.getByCultureId(id);
        responseMessage.setMessage(cultureRawDatas);

        return responseMessage;
    }

    // 전체 리스트 조회 테스트
    @GetMapping("/test/{pNo}/{size}")
    public ResponseMessage testall(@PathVariable("pNo") int pNo, @PathVariable("size") int size) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        PageRequest request = new PageRequest(pNo-1,size, Sort.Direction.DESC,"id");
        responseMessage.setMessage(cultureService.getAll(request));
        return responseMessage;
    }
}
