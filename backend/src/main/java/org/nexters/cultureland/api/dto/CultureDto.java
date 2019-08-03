package org.nexters.cultureland.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CultureDto {
    private String cultureName;
    private Long id;
    private String imageUrl;

    public CultureDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
