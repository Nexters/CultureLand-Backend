package org.nexters.cultureland.api.diary;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.repository.DiaryRepository;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;

    @MockBean
    private DiaryRepository diaryRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    void 기록_수정_테스트() {
        Diary testDiary = Diary.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        given(diaryRepository.findById(1L)).willReturn(Optional.ofNullable(testDiary));
        when(diaryRepository.save(any())).thenReturn(testDiary);

        // when
        String updateTitle = "제목";
        String updateContent = "내용";
        Diary updateDiary = diaryService.updateDiaryOf(1L, new DiaryDto(updateTitle, updateContent));

        // then
        assertThat(updateDiary.getTitle()).isEqualTo(updateTitle);
        assertThat(updateDiary.getContent()).isEqualTo(updateContent);
    }
}
