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
    private CultureServiceImpl cultureService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 전체조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 인기순정렬_전체조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/1/10/id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }


    @Test
    void 카테고리로조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos?category=concert"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 검색어버튼조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos?find=스릴"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 검색어로_제목_조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/230"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 상세조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/230"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 제목조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos?title=스릴"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void 페이지조회_테스트() throws Exception {
        mockMvc.perform(get("/cultureInfos/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}
