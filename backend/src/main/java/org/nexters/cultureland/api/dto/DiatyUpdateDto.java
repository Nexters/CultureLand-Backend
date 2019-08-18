package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiatyUpdateDto {
    private String title;
    private String sometime;
    private String place;
    private String withWho;
    private String content;

    public DiatyUpdateDto(final String title, final String sometime, final String place, final String withWho, final String content) {
        this.title = title;
        this.sometime = sometime;
        this.place = place;
        this.withWho = withWho;
        this.content = content;
    }
}
