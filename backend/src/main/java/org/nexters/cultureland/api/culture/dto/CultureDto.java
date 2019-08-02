package org.nexters.cultureland.api.culture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.culture.model.CultureRawData;

import java.time.LocalDateTime;
import java.util.List;

@Getter @NoArgsConstructor
public class CultureDto {
    private String cultureName;
    private Long id;
    private String imageUrl;

    public CultureDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
