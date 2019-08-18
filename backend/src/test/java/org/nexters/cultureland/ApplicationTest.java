package org.nexters.cultureland;

import org.junit.jupiter.api.Test;
import org.nexters.cultureland.api.service.CultureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private CultureServiceImpl cultureService;

    //cultureInfos?category={category}&sort={sort}&page={page}
    @Test
    void 전체조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 인기순정렬_전체조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos?sort=popular"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }


    @Test
    void 카테고리로조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos?category=concert&sort=popular"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 검색어버튼조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/title/스릴"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 검색어로_제목_조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/search?query=스릴"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 상세조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/id/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void counts_조회() throws Exception {
        mockMvc.perform(get("/diaries/counts"))
                .andDo(print());
    }
}
