package org.nexters.cultureland.api.diary.controller;

import org.nexters.cultureland.api.diary.Diaries;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
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
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setPath(request.getServletPath());

        long userId = (long) request.getAttribute("userId");
        Diaries diaries = diaryService.fetchUserDiaries(userId);
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping("/{diaryId}")
    public ResponseMessage readUserDiary(@PathVariable Long diaryId,
                                         HttpServletRequest request) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setPath(request.getServletPath());

        long userId = (long) request.getAttribute("userId");
        DiaryDto diary = diaryService.getDiaryOf(userId, diaryId);
        responseMessage.setMessage(diary);

        return responseMessage;
    }

    @PostMapping
    public ResponseMessage createUserDiary(@RequestBody DiaryDto diaryDto,
                                           HttpServletRequest request) {
        System.out.println(diaryDto);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setPath(request.getServletPath());

        long userId = (long) request.getAttribute("userId");
        DiaryDto diary = diaryService.create(userId, diaryDto);
        responseMessage.setMessage(diary);

        return responseMessage;
    }


    @PutMapping("/{diaryId}")
    public ResponseMessage updateUserDiary(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto,
                                           HttpServletRequest request) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setPath(request.getServletPath());

        long userId = (long) request.getAttribute("userId");
        DiaryDto diary = diaryService.updateDiaryOf(userId, diaryId, diaryDto);
        responseMessage.setMessage(diary);
        return responseMessage;
    }

    @DeleteMapping("/{diaryId}")
    public ResponseMessage deleteUserDiary(@PathVariable Long diaryId,
                                           HttpServletRequest request) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setPath(request.getServletPath());

        long userId = (long) request.getAttribute("userId");
        diaryService.deleteDiaryOf(userId, diaryId);
        responseMessage.setMessage("성공적으로 삭제되었습니다.");

        return responseMessage;
    }
}
