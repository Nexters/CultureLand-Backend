package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CultureTitleDto {
    private Long id;
    private String title;

    public CultureTitleDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
