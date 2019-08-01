package org.nexters.cultureland.api.diary.controller;

import org.nexters.cultureland.api.diary.Diaries;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(final DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/all")
    public ResponseMessage readAllUserDiaries() {
        Diaries diaries = diaryService.fetchDiaries();
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping
    public ResponseMessage readUserDiaries(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        Diaries diaries = diaryService.fetchUserDiaries(userId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping("/{diaryId}")
    public ResponseMessage readUserDiary(@PathVariable Long diaryId,
                                         HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        DiaryDto diary = diaryService.getDiaryDto(userId, diaryId);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }

    @PostMapping
    public ResponseMessage createUserDiary(@RequestBody DiaryDto diaryDto,
                                           HttpServletRequest request) {
        System.out.println(diaryDto);
        long userId = (long) request.getAttribute("userId");
        DiaryDto diary = diaryService.create(userId, diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }


    @PutMapping("/{diaryId}")
    public ResponseMessage updateUserDiary(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto,
                                           HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        Diary diary = diaryService.updateDiaryOf(userId, diaryId, diaryDto);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }

    @DeleteMapping("/{diaryId}")
    public ResponseMessage deleteUserDiary(@PathVariable Long diaryId,
                                           HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        diaryService.deleteDiaryOf(userId, diaryId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage("성공적으로 삭제되었습니다.");
        responseMessage.setPath(request.getServletPath());
        return responseMessage;
    }
}
