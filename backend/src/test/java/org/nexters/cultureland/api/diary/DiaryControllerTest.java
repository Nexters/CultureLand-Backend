package org.nexters.cultureland.api.diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiaryController.class)
public class DiaryControllerTest {

    private static final String BASE_URL = "/users/1/diaries";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryRepository diaryRepository;

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

    @Test
    void diary_생성_테스트() throws Exception {
        String title = "title";
        String content = "content";
        Diary createDiary = Diary.builder()
                .id(1L)
                .title(title)
                .content(content)
                .build();

        given(diaryRepository.save(any(Diary.class))).willReturn(createDiary);

        mockMvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("title", title)
                    .param("content", content)
                ).andDo(print())
                .andExpect(status().isCreated());
    }
}
