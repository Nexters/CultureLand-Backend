package org.nexters.cultureland.api.repo;

import org.junit.jupiter.api.Test;
import org.nexters.cultureland.api.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        Long result = diaryRepository.countByUserFavoriteDiary(1L);

        System.out.println(result);
    }

    @Test
    void 월별_내가_쓴글_조회() {
        Page<Diary> byUserAndSometime = diaryRepository.findByUserAndSometime("201908", 1, PageRequest.of(0, 2));

        byUserAndSometime.getContent().forEach(System.out::println);
    }
}
