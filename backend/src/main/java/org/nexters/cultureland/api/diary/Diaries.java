package org.nexters.cultureland.api.diary;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class Diaries {
    private List<DiaryDto> diaries;

    public Diaries(final List<DiaryDto> diaries) {
        this.diaries = Collections.unmodifiableList(diaries);
    }
    public void addDiary(DiaryDto diary){
        if(this.diaries == null){
            diaries = new ArrayList<>();
        }
        diaries.add(diary);
    }
    public List<DiaryDto> getDiaries() {
        return diaries;
    }
}
