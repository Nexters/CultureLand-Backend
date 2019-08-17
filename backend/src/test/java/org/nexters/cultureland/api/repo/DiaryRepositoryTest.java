package org.nexters.cultureland.api.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiaryRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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

    @Test
    void 전쳬_기록_갯수() {
        List<Object[]> results = diaryRepository.countByCategories(1L);
        for (Object[] result : results) {
            System.out.println(result[0]);
            System.out.println(result[1]);
        }
    }

    @Test
    void 좋아요_갯수() {
        Integer result = diaryRepository.countByUserFavoriteDiary(1L);

        System.out.println(result);
    }
}
