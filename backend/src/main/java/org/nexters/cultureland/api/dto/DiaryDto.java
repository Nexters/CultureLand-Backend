package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.Diary;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDto {
    private long id;
    private String title;
    private LocalDate sometime;
    private String place;
    private String withWho;
    private String content;
    private String imageUrl;
    private boolean favorite;
    private Culture culture;

    public DiaryDto(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.sometime = diary.getSometime();
        this.content = diary.getContent();
        this.place = diary.getPlace();
        this.withWho = diary.getWithWho();
        this.favorite = diary.isFavorite();
        this.imageUrl = diary.getImageUrl();
        this.culture = diary.getCulture();
    }
}
