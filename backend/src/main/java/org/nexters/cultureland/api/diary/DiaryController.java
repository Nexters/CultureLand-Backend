package org.nexters.cultureland.api.diary;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users/{userId}/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(final DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public ResponseEntity<Diaries> readAllUserDiaries() {
        return ResponseEntity.ok(diaryService.fetchDiaries());
    }

    @PostMapping
    public ResponseEntity<Diary> createUserDiary(@PathVariable Long userId, DiaryDto diaryDto) {
        Diary diary = diaryService.create(diaryDto);

        String location = String.format("/users/%d/diaries/%d", userId, diary.getId());
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Location", location);

        return new ResponseEntity<>(diary, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<Diary> readUserDiary(@PathVariable Long userId, @PathVariable Long diaryId) {
        return new ResponseEntity<>(diaryService.getDiaryOf(diaryId), HttpStatus.OK);
    }

    @PutMapping("/{diaryId}")
    public ResponseEntity<Diary> updateUserDiary(@PathVariable Long userId, @PathVariable Long diaryId, DiaryDto diaryDto) {
        return new ResponseEntity<>(diaryService.updateDiaryOf(diaryId, diaryDto), HttpStatus.OK);
    }
}
