package org.nexters.cultureland;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.nexters.cultureland.controller.CultureController;
import org.nexters.cultureland.model.service.CultureService;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(CultureController.class)
public class CultureControllerTest {


    private static final String BASE_URL = "/cultureInfos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CultureService cultureService;

    private Culture culture;

    @Test
    void diary_전체_조회() throws Exception {
        when(cultureService.getAllCulture()).thenReturn(Arrays.asList(culture));

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print());
    }



}