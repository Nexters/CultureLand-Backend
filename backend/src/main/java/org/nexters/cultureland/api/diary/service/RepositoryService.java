package org.nexters.cultureland.api.diary.service;

import org.nexters.cultureland.api.diary.Diaries;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.diary.repository.DiaryRepository;
import org.nexters.cultureland.api.user.exception.UserNotFoundException;
import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.repo.UserRepository;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.nexters.cultureland.common.excepion.NotFoundDiaryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {
    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않는 기록입니다.";
    private static final String FORBIDDEN_ERROR_MESSAGE = "권한이 없습니다.";

    private final DiaryRepository diaryRepository; // repository 리팩토링 필요
    private final UserRepository userRepository;

    public RepositoryService(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }
    public Diaries readAllDiaries(){
        return diaryEntityToDto(diaryRepository.findAll());
    }
    public Diaries readUserDiaries(long userId) {
        User user = findUser(userId);

        List<Diary> diaryEntites = diaryRepository.findByUser(user);
        Diaries diaries = diaryEntityToDto(diaryEntites);
        return diaries;
    }

    public DiaryDto readDiary(long userId, final Long diaryId){
        Diary diary = findDiaryEntity(userId, diaryId);
        return new DiaryDto(diary);
    }

    public DiaryDto createDiary(DiaryDto diaryDto, long userId) {
        User user = findUser(userId);
        Diary diary = diaryRepository.save(new Diary(diaryDto, user));

        return new DiaryDto(diary);
    }

    public DiaryDto updateDiary(long userId, final Long diaryId, DiaryDto diaryDto) {
        Diary diary = findDiaryEntity(userId, diaryId);
        diary.update(diaryDto);
        Diary savedDiary = diaryRepository.save(diary);

        return new DiaryDto(savedDiary);
    }

    public void deleteDiary(long userId, final Long diaryId){
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

    private void checkForbiddenRequest(long userId, Diary diary){
        if(!diary.getUser().equals(findUser(userId))){
            throw new ForbiddenException(FORBIDDEN_ERROR_MESSAGE);
        }
    }
}
