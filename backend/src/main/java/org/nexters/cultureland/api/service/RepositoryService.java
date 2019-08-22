package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.*;
import org.nexters.cultureland.api.exception.NotFoundDiaryException;
import org.nexters.cultureland.api.exception.UserNotFoundException;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.Diary;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.CultureRepository;
import org.nexters.cultureland.api.repo.DiaryRepository;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {
    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않는 기록입니다.";
    private static final String FORBIDDEN_ERROR_MESSAGE = "권한이 없습니다.";

    private final DiaryRepository diaryRepository; // repository 리팩토링 필요
    private final UserRepository userRepository;
    private final CultureRepository cultureRepository;

    public RepositoryService(final DiaryRepository diaryRepository,
                             final UserRepository userRepository, final CultureRepository cultureRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.cultureRepository = cultureRepository;
    }

    public Page<DiaryDto> readUserDiaries(long userId, Category category, String date, Pageable pageable) {
        User user = findUser(userId);

        if (category != null) {
            if (category == Category.LIKE) {
                return diaryRepository.findByFavoriteIsTrue(pageable);
            }

            return diaryRepository.findByCulture_CultureNameAndUser(category.name(), user, pageable);
        } else if (date != null) {
            Page<Diary> diaryPage = diaryRepository.findByUserAndSometime(date, user.getSeq(), pageable);
            return diaryPage.map(DiaryDto::new);
        }

        return Page.empty(pageable);
    }

    public DiaryDto readDiary(long userId, final Long diaryId) {
        Diary diary = findDiaryEntity(userId, diaryId);
        return new DiaryDto(diary);
    }

    // TODO : culture Service로 메서드 이동 (리펙토링)
    public DiaryDto createDiary(DiaryCreateDto diaryDto, long userId) {
        User user = findUser(userId);
        Culture culture = cultureRepository.findByCultureName(diaryDto.getCultureName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        Diary diary = diaryRepository.save(new Diary(diaryDto, culture, user));

        return new DiaryDto(diary);
    }

    public DiaryDto updateDiary(long userId, final Long diaryId, DiatyUpdateDto diaryDto) {
        Diary diary = findDiaryEntity(userId, diaryId);
        diary.update(diaryDto);
        Diary savedDiary = diaryRepository.save(diary);

        return new DiaryDto(savedDiary);
    }

    public void deleteDiary(long userId, final Long diaryId) {
        Diary diary = findDiaryEntity(userId, diaryId);
        diaryRepository.delete(diary);
    }

    private Diaries diaryEntityToDto(List<Diary> diaryEntities) {
        Diaries diaries = new Diaries();
        for (Diary diary : diaryEntities) {
            diaries.addDiary(new DiaryDto(diary));
        }
        return diaries;
    }

    private User findUser(long userId) {
        return userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }

    private Diary findDiaryEntity(long userId, final Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NotFoundDiaryException(NOT_FOUND_ERROR_MESSAGE));
        checkForbiddenRequest(userId, diary);
        return diary;
    }

    private void checkForbiddenRequest(long userId, Diary diary) {
        if (!diary.getUser().equals(findUser(userId))) {
            throw new ForbiddenException(FORBIDDEN_ERROR_MESSAGE);
        }
    }

    public DiaryDto like(final long userId, final Long diaryId) {
        Diary diary = findDiaryEntity(userId, diaryId);
        diary.like();

        return new DiaryDto(diary);
    }

    public List<DiaryCountDto> countByUserGroupedMonth(Long userId, String year) {
        User user = findUser(userId);
        List<Object[]> queryResult = diaryRepository.countByUser(user.getSeq(), year);


        return queryResult.stream()
                .map((result) ->
                        DiaryCountDto.builder()
                                .monthTime((String) result[0])
                                .count(((BigInteger) result[1]).intValue())
                                .imageUrl(diaryRepository.findDefaultImageByMonth(user.getSeq(), year + result[0]).orElse(""))
                                .build()
                ).collect(Collectors.toList());
    }

    public HashMap<String, Long> countByUserGroupedCategory(final Long userId) {
        HashMap<String, Long> diaryCategoryCount = new HashMap<>();
        User user = findUser(userId);
        List<Object[]> countOfGroupedCategory = diaryRepository.countByCategories(user.getSeq());

        long total = 0;

        for (Object[] countOfCategory : countOfGroupedCategory) {
            Long count = (Long) countOfCategory[1];
            diaryCategoryCount.put(countOfCategory[0] + "Count", count);
            total += count;
        }

        diaryCategoryCount.put("totalNumberOfDiaryCount", total);
        diaryCategoryCount.put("likedDiaryCount", diaryRepository.countByUserFavoriteDiary(userId));

        return diaryCategoryCount;
    }
}
