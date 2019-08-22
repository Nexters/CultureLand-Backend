package org.nexters.cultureland.api.dto;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Diaries {
    private List<DiaryDto> diaries;

    public Diaries(final List<DiaryDto> diaries) {
        this.diaries = diaries;
    }

    public void addDiary(DiaryDto diary) {
        if (this.diaries == null) {
            diaries = new ArrayList<>();
        }
        diaries.add(diary);
    }

    public List<DiaryDto> getDiaries() {
        return diaries;
    }
}
