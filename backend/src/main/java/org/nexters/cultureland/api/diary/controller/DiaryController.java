package org.nexters.cultureland.api.diary.controller;

import org.nexters.cultureland.api.diary.Diaries;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
    public ResponseMessage createUserDiary(DiaryDto diaryDto) {
        Diary diary = diaryService.create(diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);

        return responseMessage;
    }

    @GetMapping("/{diaryId}")
    public ResponseMessage readUserDiary(@PathVariable Long diaryId) {
        Diary diary = diaryService.getDiaryOf(diaryId);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        return responseMessage;
    }

    @PutMapping("/{diaryId}")
    public ResponseMessage updateUserDiary(@PathVariable Long diaryId, DiaryDto diaryDto) {
        Diary diary = diaryService.updateDiaryOf(diaryId, diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        return responseMessage;
    }

    @DeleteMapping("/{diaryId}")
    public ResponseMessage deleteUserDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiaryOf(diaryId);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage("성공적으로 삭제되었습니다.");
        return responseMessage;
    }
}
