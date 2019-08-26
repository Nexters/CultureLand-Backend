package org.nexters.cultureland.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryCountDto {

    private String monthTime;
    private int count;
    private String imageUrl;

    @Builder
    public DiaryCountDto(final String monthTime, final int count, final String imageUrl) {
        this.monthTime = monthTime;
        this.count = count;
        this.imageUrl = imageUrl;
    }
}
