package org.nexters.cultureland.api.diary;

import lombok.*;
import org.nexters.cultureland.api.diary.model.Diary;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDto {
    private long id;
    private String title;
    private LocalDateTime sometime;
    private String place;
    private String withWho;
    private String content;

    @Builder
    public DiaryDto(String title, LocalDateTime sometime, String place, String withWho, String content) {
        this.title = title;
        this.sometime = sometime;
        this.place = place;
        this.withWho = withWho;
        this.content = content;
    }

    public DiaryDto(Diary diary){
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.sometime = diary.getSometime();
        this.content = diary.getContent();
        this.place = diary.getPlace();
        this.withWho = diary.getWithWho();
    }
}
