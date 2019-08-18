package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DiaryService {
    private final RepositoryService repositoryService;

    public DiaryService(final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Page<DiaryDto> fetchUserDiaries(long userId, Category category, String date, Pageable pageable) {
        return repositoryService.readUserDiaries(userId, category, date, pageable);
    }

    public DiaryDto create(long userId, final DiaryCreateDto diaryDto) {
        DiaryDto diary = repositoryService.createDiary(diaryDto, userId);
        return diary;
    }

    public DiaryDto getDiaryOf(long userId, final Long diaryId) {
        DiaryDto diary = repositoryService.readDiary(userId, diaryId);
        return diary;
    }

    public DiaryDto updateDiaryOf(long userId, final Long diaryId, DiatyUpdateDto diaryDto) {
        DiaryDto diary = repositoryService.updateDiary(userId, diaryId, diaryDto);
        return diary;
    }

    public void deleteDiaryOf(long userId, final Long diaryId) {
        repositoryService.deleteDiary(userId, diaryId);
    }

    public DiaryDto like(final long userId, final Long diaryId) {
        return repositoryService.like(userId, diaryId);
    }

    public List<DiaryCountDto> countByUserGroupedMonth(Long userId, String year) {
        return repositoryService.countByUserGroupedMonth(userId, year);
    }

    public HashMap<String, Integer> countByUserGroupedCategory(Long userId) {
        return repositoryService.countByUserGroupedCategory(userId);
    }
}