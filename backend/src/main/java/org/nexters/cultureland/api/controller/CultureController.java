package org.nexters.cultureland.controller;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.dto.CultureDto;
import org.nexters.cultureland.model.CultureRawData;
import org.nexters.cultureland.service.CultureServiceImpl;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //문화생활 상세조회
    @GetMapping("/{cultureInfoId}")
    public ResponseMessage readDetailById(@PathVariable("cultureInfoId") Long id) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        CultureRawData cultureRawDatas = cultureService.getByCultureId(id);
        responseMessage.setMessage(cultureRawDatas);

        return responseMessage;
    }

    // 전체 리스트 조회 테스트
/*    @GetMapping("/test/test")
    public ResponseEntity testall() {
        return ResponseEntity.ok(cultureService.getAll());
    }*/
}
