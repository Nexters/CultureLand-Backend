package org.nexters.cultureland.api.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
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
