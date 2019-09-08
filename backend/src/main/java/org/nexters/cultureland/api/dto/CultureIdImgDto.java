package org.nexters.cultureland.api.dto;

import lombok.*;
import org.nexters.cultureland.api.model.CultureRawData;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CultureIdImgDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String startDate;
    private String endDate;

    @Builder
    public CultureIdImgDto(Long id, String imageUrl, String startDate, String endDate, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public CultureIdImgDto(CultureRawData cultureRawData) {
        this.id = cultureRawData.getId();
        this.imageUrl = cultureRawData.getImageUrl();
        this.startDate = cultureRawData.getStartDate();
        this.endDate = cultureRawData.getEndDate();
        this.title = cultureRawData.getTitle();
    }


}
