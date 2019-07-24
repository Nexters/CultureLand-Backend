package org.nexters.cultureland.api.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDto {
    private String title;
    private String content;

    public DiaryDto(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}
