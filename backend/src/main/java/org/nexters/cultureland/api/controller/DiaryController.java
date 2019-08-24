package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.dto.*;
import org.nexters.cultureland.api.service.DiaryService;
import org.nexters.cultureland.api.service.S3Service;
import org.nexters.cultureland.common.LoginUser;
import org.nexters.cultureland.common.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/diaries")
public class DiaryController {
    private static final Logger log = LoggerFactory.getLogger(DiaryController.class);

    private final DiaryService diaryService;
    private final S3Service s3Service;

    public DiaryController(final DiaryService diaryService, final S3Service s3Service) {
        this.diaryService = diaryService;
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseMessage readUserDiaries(@LoginUser long userId, @RequestParam(required = false) Category category,
                                           @RequestParam(required = false) String date, Pageable pageable) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        Page<DiaryDto> diaries = diaryService.fetchUserDiaries(userId, category, date, pageable);
        responseMessage.setMessage(diaries);
        return responseMessage;
    }

    @GetMapping("/all/counts")
    public ResponseMessage countByUserGroupedCategory(@LoginUser long userId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        HashMap<String, Long> counts = diaryService.countByUserGroupedCategory(userId);
        responseMessage.setMessage(counts);
        return responseMessage;
    }

    @GetMapping("/all/summaries")
    public ResponseMessage summaryUserDiaries(@LoginUser long userId, @RequestParam(defaultValue = "today") String year) {
        if (year.equals("today")) {
            year = LocalDate.now().getYear() + "";
        }

        List<DiaryCountDto> diaryCountDtos = diaryService.countByUserGroupedMonth(userId, year);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diaryCountDtos);

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
        log.info("create diary: {} by {}", diaryDto, userId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
//        responseMessage.setPath(request.getServletPath());

        DiaryDto diaryResponse = diaryService.create(userId, diaryDto);
        responseMessage.setMessage(diaryResponse);

        log.info("diary created");
        return responseMessage;
    }

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMessage uploadDiaryImage(@RequestPart MultipartFile image) throws IOException {
        log.info("image: {}", image);
        String imageUrl = s3Service.upload(image);
        log.info("image uploaded");
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

    @GetMapping("/{diaryId}/like")
    public ResponseMessage like(@PathVariable final long diaryId, @LoginUser long userId) {
        DiaryDto diary = diaryService.like(userId, diaryId);

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage(diary);

        return responseMessage;
    }
}
