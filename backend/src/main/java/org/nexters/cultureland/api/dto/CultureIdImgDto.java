package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nexters.cultureland.api.model.CultureRawData;


@NoArgsConstructor
@Getter
@Setter
public class CultureIdImgDto {
    private Long id;
    private String imageUrl;
    private String startDate;
    private String endDate;

    public CultureIdImgDto(Long id, String imageUrl, String startDate, String endDate) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CultureIdImgDto(CultureRawData cultureRawData) {
        this.id = cultureRawData.getId();
        this.imageUrl = cultureRawData.getImageUrl();
        this.startDate = cultureRawData.getStartDate();
        this.endDate = cultureRawData.getEndDate();
    }


}
