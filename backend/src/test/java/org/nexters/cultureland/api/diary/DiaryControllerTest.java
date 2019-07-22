package org.nexters.cultureland.api.diary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiaryController.class)
public class DiaryControllerTest {

    private static final String BASE_URL = "/users/1/diaries";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryRepository diaryRepository;

    @Test
    void diary_전체_조회() throws Exception {
        Diary diary = Diary.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        when(diaryRepository.findAll()).thenReturn(Arrays.asList(diary));

        mockMvc.perform(get(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("diaries.[0].id").value(1L))
                .andExpect(jsonPath("diaries.[0].title").value("title"))
                .andExpect(jsonPath("diaries.[0].content").value("content"));
    }
}
