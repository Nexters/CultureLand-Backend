package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.dto.Diaries;
import org.nexters.cultureland.api.dto.DiaryCreateDto;
import org.nexters.cultureland.api.dto.DiaryDto;
import org.nexters.cultureland.api.dto.DiatyUpdateDto;
import org.nexters.cultureland.api.service.DiaryService;
import org.nexters.cultureland.api.service.S3Service;
import org.nexters.cultureland.common.LoginUser;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/diaries")
public class DiaryController {

    private final DiaryService diaryService;
    private final S3Service s3Service;

    public DiaryController(final DiaryService diaryService, final S3Service s3Service) {
        this.diaryService = diaryService;
        this.s3Service = s3Service;
    }

    // TODO : 모든 유저 기록 보여주기 삭제
    @GetMapping("/all")
    public ResponseMessage readAllUserDiaries() {
        Diaries diaries = diaryService.fetchDiaries();
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping
    public ResponseMessage readUserDiaries(@LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        Diaries diaries = diaryService.fetchUserDiaries(userId);
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping("/{diaryId}")
    public ResponseMessage readUserDiary(@PathVariable Long diaryId,
                                         @LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        DiaryDto diary = diaryService.getDiaryOf(userId, diaryId);
        responseMessage.setMessage(diary);

        return responseMessage;
    }

    @PostMapping
    public ResponseMessage createUserDiary(@RequestBody DiaryCreateDto diaryDto,
                                           @LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        DiaryDto diaryResponse = diaryService.create(userId, diaryDto);
        responseMessage.setMessage(diaryResponse);

        return responseMessage;
    }

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMessage uploadDiaryImage(@RequestPart MultipartFile image) throws IOException {
        String imageUrl = s3Service.upload(image);
        return ResponseMessage.builder()
                .code(200)
                .message(imageUrl)
                .build();
    }


    @PutMapping("/{diaryId}")
    public ResponseMessage updateUserDiary(@PathVariable Long diaryId, @RequestBody DiatyUpdateDto diaryDto,
                                           @LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        DiaryDto diary = diaryService.updateDiaryOf(userId, diaryId, diaryDto);
        responseMessage.setMessage(diary);
        return responseMessage;
    }

    @DeleteMapping("/{diaryId}")
    public ResponseMessage deleteUserDiary(@PathVariable Long diaryId,
                                           @LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        diaryService.deleteDiaryOf(userId, diaryId);
        responseMessage.setMessage("성공적으로 삭제되었습니다.");

        return responseMessage;
    }
}
