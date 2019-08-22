package org.nexters.cultureland.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiaryCreateDto {
    private String title;
    private String sometime;
    private String place;
    private String withWho;
    private String content;
    private String cultureName;
    private String imageUrl;


    @Builder
    private DiaryCreateDto(final String title, final String sometime, final String place, final String withWho,
                           final String content, final String cultureName, String imageUrl) {
        this.title = title;
        this.sometime = sometime;
        this.place = place;
        this.withWho = withWho;
        this.content = content;
        this.cultureName = cultureName;
        this.imageUrl = imageUrl;
    }
}
