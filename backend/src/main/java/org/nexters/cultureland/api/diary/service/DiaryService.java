package org.nexters.cultureland.api.diary.service;

import org.modelmapper.ModelMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiaryService {

    private static final String NOT_FOUND_ERROR_MESSAGE = "존재하지 않는 기록입니다.";
    private static final String FORBIDDEN_ERROR_MESSAGE = "권한이 없습니다.";
    private final DiaryRepository diaryRepository; // repository 리팩토링 필요
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public DiaryService(final DiaryRepository diaryRepository, final UserRepository userRepository, final ModelMapper modelMapper) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Diaries fetchDiaries() {
        return diaryEntityToDto(diaryRepository.findAll());
    }

    public Diaries fetchUserDiaries(long userId) {
        User user = userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_ERROR_MESSAGE));
        return diaryEntityToDto(diaryRepository.findByUser(user));
    }

    public DiaryDto create(long userId, final DiaryDto diaryDto) {
        User user = findUser(userId);
        Diary savedDiary =  diaryRepository.save(new Diary(diaryDto, user));
        return new DiaryDto(savedDiary);
    }
    public DiaryDto getDiaryDto(long userId, final Long diaryId){
        Diary diary =  getDiaryOf(userId, diaryId);
        return new DiaryDto(diary);
    }

    private Diary getDiaryOf(long userId, final Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NotFoundDiaryException(NOT_FOUND_ERROR_MESSAGE));
        checkForbiddenRequest(userId, diary);
        return diary;
    }

    public Diary updateDiaryOf(long userId, final Long diaryId, DiaryDto diaryDto) {
        Diary diary = getDiaryOf(userId, diaryId);

        diary.update(diaryDto);
        return diaryRepository.save(diary);
    }

    public void deleteDiaryOf(long userId, final Long diaryId) {
        Diary diary = getDiaryOf(userId, diaryId);

        diaryRepository.delete(diary);
    }

    private void checkForbiddenRequest(long userId, Diary diary){
        if(!diary.getUser().equals(findUser(userId))){
            throw new ForbiddenException(FORBIDDEN_ERROR_MESSAGE);
        }
    }
    private User findUser(long userId) {
        return userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }

    private Diaries diaryEntityToDto(List<Diary> diaryEntities){
        Diaries diaries = new Diaries();
        for(Diary diary : diaryEntities){
            diaries.addDiary(new DiaryDto(diary));
        }
        return diaries;
    }
}