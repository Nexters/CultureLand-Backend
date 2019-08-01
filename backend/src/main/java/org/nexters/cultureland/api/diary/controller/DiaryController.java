package org.nexters.cultureland.api.diary.controller;

import org.nexters.cultureland.api.diary.Diaries;
import org.nexters.cultureland.api.diary.DiaryDto;
<<<<<<< HEAD
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.common.ResponseMessage;
=======
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
>>>>>>> 70dcb2ed5f100a7f957668c6b2e51e47645c6064
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(final DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public ResponseMessage readAllUserDiaries() {
        Diaries diaries = diaryService.fetchDiaries();

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @PostMapping
    public ResponseMessage createUserDiary(DiaryDto diaryDto,
                                           HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        Diary diary = diaryService.create(diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }

    @GetMapping("/{diaryId}")
    public ResponseMessage readUserDiary(@PathVariable Long diaryId,
                                         HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        Diary diary = diaryService.getDiaryOf(diaryId);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }

    @PutMapping("/{diaryId}")
    public ResponseMessage updateUserDiary(@PathVariable Long diaryId, DiaryDto diaryDto,
                                           HttpServletRequest request){
        long userId = (long) request.getAttribute("userId");
        Diary diary = diaryService.updateDiaryOf(diaryId, diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }

    @DeleteMapping("/{diaryId}")
    public ResponseMessage deleteUserDiary(@PathVariable Long diaryId,
                                           HttpServletRequest request) {
        diaryService.deleteDiaryOf(diaryId);
        long userId = (long) request.getAttribute("userId");
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage("성공적으로 삭제되었습니다.");
        responseMessage.setMessage(request.getServletPath());
        return responseMessage;
    }
}
