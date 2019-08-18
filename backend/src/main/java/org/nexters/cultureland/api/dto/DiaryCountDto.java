package org.nexters.cultureland.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryCountDto {

    private String monthTime;
    private int count;

    @Builder
    private DiaryCountDto(final String monthTime, final int count) {
        this.monthTime = monthTime;
        this.count = count;
    }
}
