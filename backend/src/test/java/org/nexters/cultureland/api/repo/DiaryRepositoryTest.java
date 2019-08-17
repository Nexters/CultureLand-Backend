package org.nexters.cultureland.api.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void name() {
        List<Object[]> diaryCountDtos = diaryRepository.countByUser(1L, "2019");
        diaryRepository.flush();

        for (Object[] diaryCountDto : diaryCountDtos) {
            System.out.println(diaryCountDto[0]);
            System.out.println(diaryCountDto[1]);
        }
    }
}
