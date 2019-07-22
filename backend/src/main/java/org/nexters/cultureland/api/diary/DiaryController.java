package org.nexters.cultureland.api.diary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}/diaries")
public class DiaryController {

    private final DiaryRepository diaryRepository;

    public DiaryController(final DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @GetMapping
    public ResponseEntity<Diaries> readAllUserDiaries() {
        Diaries diaries = new Diaries(diaryRepository.findAll());
        return ResponseEntity.ok(diaries);
    }
}
