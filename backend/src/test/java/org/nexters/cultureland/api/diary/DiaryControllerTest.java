package org.nexters.cultureland.api.diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nexters.cultureland.api.diary.excepion.NotFoundDiaryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiaryController.class)
public class DiaryControllerTest {

    private static final String BASE_URL = "/users/1/diaries";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryService diaryService;

    private Diary diary;

    @BeforeEach
    void setUp() {
        diary = Diary.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();
    }

    @Test
    void diary_전체_조회() throws Exception {
        when(diaryService.fetchDiaries()).thenReturn(new Diaries(Arrays.asList(diary)));

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("diaries.[0].id").value(1L))
                .andExpect(jsonPath("diaries.[0].title").value("title"))
                .andExpect(jsonPath("diaries.[0].content").value("content"));
    }

    @Test
    void diary_생성() throws Exception {
        String title = "title";
        String content = "content";
        Diary createDiary = Diary.builder()
                .id(1L)
                .title(title)
                .content(content)
                .build();

        given(diaryService.create(any(DiaryDto.class))).willReturn(createDiary);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("content", content)
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void diary_id_1번_데이터_조회() throws Exception {
        when(diaryService.getDiaryOf(1L)).thenReturn(diary);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
    }

    @Test
    void 존재하지_않는_diary_조회_에러() throws Exception {
        String errorMessage = "존재하지 않는 기록입니다.";
        when(diaryService.getDiaryOf(2L)).thenThrow(new NotFoundDiaryException(errorMessage));

        mockMvc.perform(get(BASE_URL + "/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    void diary_1번_데이터_수정() throws Exception {
        given(diaryService.getDiaryOf(1L)).willReturn(diary);

        Diary updateDiary = Diary.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .build();
        when(diaryService.updateDiaryOf(1L, new DiaryDto("제목", "내용"))).thenReturn(updateDiary);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("title", "제목")
                .param("content", "내용")
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"));
    }
}