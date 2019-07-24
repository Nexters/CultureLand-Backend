package org.nexters.cultureland.api.diary;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.diary.excepion.NotFoundDiaryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final ModelMapper modelMapper;

    public DiaryService(final DiaryRepository diaryRepository, final ModelMapper modelMapper) {
        this.diaryRepository = diaryRepository;
        this.modelMapper = modelMapper;
    }

    public Diaries fetchDiaries() {
        return new Diaries(diaryRepository.findAll());
    }

    public Diary create(final DiaryDto diaryDto) {
        Diary diary = diaryRepository.save(modelMapper.map(diaryDto, Diary.class));

        return diary;
    }

    public Diary getDiaryOf(final Long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NotFoundDiaryException("존재하지 않는 기록입니다."));
    }

    public Diary updateDiaryOf(final Long diaryId, DiaryDto diaryDto) {
        Diary diary = getDiaryOf(diaryId);
        diary.update(diaryDto);
        return diaryRepository.save(diary);
    }
}
