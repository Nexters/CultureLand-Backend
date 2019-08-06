package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.CultureRawData;


@NoArgsConstructor
@Getter
@Setter
public class CultureIdImgDto {
    private Long id;
    private String imageUrl;

    public CultureIdImgDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public CultureIdImgDto(CultureRawData cultureRawData) {
        this.id = cultureRawData.getId();
        this.imageUrl = cultureRawData.getImageUrl();
    }

}
