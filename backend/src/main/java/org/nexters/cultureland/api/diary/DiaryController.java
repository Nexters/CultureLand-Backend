package org.nexters.cultureland.api.diary;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/diaries")
public class DiaryController {

    private final DiaryRepository diaryRepository;
    private final ModelMapper modelMapper;

    public DiaryController(final DiaryRepository diaryRepository, final ModelMapper modelMapper) {
        this.diaryRepository = diaryRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Diaries> readAllUserDiaries() {
        Diaries diaries = new Diaries(diaryRepository.findAll());
        return ResponseEntity.ok(diaries);
    }

    @PostMapping
    public ResponseEntity createUserDiary(@PathVariable Long userId, DiaryDto diaryDto) {
        Diary diary = diaryRepository.save(modelMapper.map(diaryDto, Diary.class));
        String location = String.format("/users/%d/diaries/%d", userId, diary.getId());

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Location", location);
        return new ResponseEntity<>(diary, headers, HttpStatus.CREATED);
    }
}
