package org.nexters.cultureland.api.diary;

import org.nexters.cultureland.api.diary.model.Diary;

import java.util.Collections;
import java.util.List;

public class Diaries {
    private final List<Diary> diaries;

    public Diaries(final List<Diary> diaries) {
        this.diaries = Collections.unmodifiableList(diaries);
    }

    public List<Diary> getDiaries() {
        return diaries;
    }
}
