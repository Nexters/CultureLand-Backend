package org.nexters.cultureland.api.diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nexters.cultureland.api.diary.controller.DiaryController;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.service.DiaryService;
import org.nexters.cultureland.common.excepion.NotFoundDiaryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiaryController.class)
public class DiaryControllerTest {

    private static final String BASE_URL = "/diaries";

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
                .sometime(LocalDateTime.now())
                .where("강남역 메리츠타워")
                .withWho("컬쳐랜드")
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
                .andExpect(jsonPath("message.diaries.[0].id").value(1L))
                .andExpect(jsonPath("message.diaries.[0].title").value("title"))
                .andExpect(jsonPath("message.diaries.[0].content").value("content"));
    }

    @Test
    void diary_생성() throws Exception {
        String title = "title";
        String content = "content";

        given(diaryService.create(any(DiaryDto.class))).willReturn(diary);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("content", content)
                .param("when", "2019-08-03")
                .param("where", "강남역 메리츠타워")
                .param("with", "컬쳐랜드")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message.id").value(1L))
                .andExpect(jsonPath("message.title").value("title"))
                .andExpect(jsonPath("message.content").value("content"));
    }

    @Test
    void diary_id_1번_데이터_조회() throws Exception {
        when(diaryService.getDiaryOf(1L)).thenReturn(diary);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message.id").value(1L))
                .andExpect(jsonPath("message.title").value("title"))
                .andExpect(jsonPath("message.content").value("content"));
    }

    @Test
    void 존재하지_않는_diary_조회_에러() throws Exception {
        String errorMessage = "존재하지 않는 기록입니다.";
        when(diaryService.getDiaryOf(2L)).thenThrow(new NotFoundDiaryException(errorMessage));

        mockMvc.perform(get(BASE_URL + "/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value(errorMessage));
    }

    @Test
    void diary_1번_데이터_수정() throws Exception {
        Diary diary = Diary.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        given(diaryService.updateDiaryOf(anyLong(), any())).willReturn(diary);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("title", "제목")
                .param("content", "내용")
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
