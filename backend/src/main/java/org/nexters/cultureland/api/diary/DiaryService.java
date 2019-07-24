package org.nexters.cultureland.api.diary;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
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

    public Diary create(DiaryDto diaryDto) {
        Diary diary = diaryRepository.save(modelMapper.map(diaryDto, Diary.class));

        return diary;
    }
}
